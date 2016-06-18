define(function (require) {

    var rest = require('rest');
    var mime = require('rest/interceptor/mime');
    var entity = require('rest/interceptor/entity');
    var render = require('./render');

    var endpointUrl, name, client;

    endpointUrl = 'http://rest-service.guides.spring.io/greeting';
    name = document.location.search.slice(1);

    client = rest
        .chain(mime, { mime: 'application/json' })
        .chain(entity);

    client({ path: endpointUrl, params: { name:  name } })
        .then(render);

});
