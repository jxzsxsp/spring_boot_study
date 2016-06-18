package hello.container;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.yarn.batch.config.EnableYarnRemoteBatchProcessing;

@Configuration
@EnableAutoConfiguration(exclude = { BatchAutoConfiguration.class })
@EnableYarnRemoteBatchProcessing
public class ContainerApplication {

	@Autowired
	private StepBuilderFactory stepBuilder;

	@Bean
	protected Tasklet tasklet() {
		return new PrintTasklet();
	}

	@Bean
	protected Step remoteStep() throws Exception {
		return stepBuilder
			.get("remoteStep")
			.tasklet(tasklet())
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ContainerApplication.class, args);
	}

}
