Ext.define('Hello.view.GreetingView', {
    extend: 'Ext.Panel',
    config: {
        fullscreen: true,
        tpl: '<p>The ID is {id}</p><p>The content is {content}</p>'
    }
});
