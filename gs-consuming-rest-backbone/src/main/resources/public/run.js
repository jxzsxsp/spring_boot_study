var curl;
(function () {

	curl({
		main: 'hello',
		packages: {
			// Your application's package
			hello: { location: 'hello' },
			// Third-party packages
			curl: { location: 'lib/curl/src/curl' },
			jquery: { location: 'lib/jquery/jquery', main: '.' },
			Backbone: { location: 'lib/backbone-amd/backbone', main: '.' },
			underscore: { location: 'lib/lodash/lodash', main: '.' }
		}
	});

}());