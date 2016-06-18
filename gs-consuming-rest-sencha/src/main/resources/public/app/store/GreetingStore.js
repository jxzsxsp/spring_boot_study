Ext.define('Hello.store.GreetingStore', {
    extend: 'Ext.data.Store',
    config: {
        model: 'Hello.model.GreetingModel',
        proxy: {
            type: 'rest',
            url: 'http://rest-service.guides.spring.io/greeting'
        },
        listeners: {
            beforeload: function () {
                var name = document.location.search.slice(1);
                this.getProxy().setExtraParam('name', name);
            }
        }
    }
});

