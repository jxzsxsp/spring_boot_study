Ext.application({
    name: 'Hello',
    models: [ 'GreetingModel' ],
    stores: [ 'GreetingStore' ],
    views: [ 'GreetingView' ],
    launch: function () {

        var view = Ext.create('Hello.view.GreetingView', {});

        Ext.create('Hello.store.GreetingStore', {
            autoLoad: true,
            listeners: {
                load: function (self, records) {
                    view.setData(records[0].getData());
                }
            }
        });

    }
});

Ext.Loader.setConfig({ disableCaching: false });
