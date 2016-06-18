package hello;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import hello.client.ClientApplication;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.yarn.boot.test.junit.AbstractBootYarnClusterTests;
import org.springframework.yarn.test.context.MiniYarnClusterTest;
import org.springframework.yarn.test.junit.ApplicationInfo;
import org.springframework.yarn.test.support.ContainerLogUtils;

@MiniYarnClusterTest
public class AppIT extends AbstractBootYarnClusterTests {

	@Test
	public void testAppSubmission() throws Exception {

		ApplicationInfo info = submitApplicationAndWait(ClientApplication.class, new String[0]);
		assertThat(info.getYarnApplicationState(), is(YarnApplicationState.FINISHED));

		List<Resource> resources = ContainerLogUtils.queryContainerLogs(getYarnCluster(), info.getApplicationId());
		assertThat(resources, notNullValue());
		assertThat(resources.size(), is(6));

		for (Resource res : resources) {
			File file = res.getFile();
			if (file.getName().endsWith("stdout")) {
				// there has to be some content in stdout file
				assertThat(file.length(), greaterThan(0l));
				if (file.getName().equals("Container.stdout")) {
					Scanner scanner = new Scanner(file);
					String content = scanner.useDelimiter("\\A").next();
					scanner.close();
					// this is what container will log in stdout
					assertThat(content, containsString("PrintTasklet says Hello"));
				}
			} else if (file.getName().endsWith("stderr")) {
				String content = "";
				if (file.length() > 0) {
					Scanner scanner = new Scanner(file);
					content = scanner.useDelimiter("\\A").next();
					scanner.close();
				}
				// can't have anything in stderr files
				assertThat("stderr file is not empty: " + content, file.length(), is(0l));
			}
		}
	}

}
