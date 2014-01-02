Ext.onReady(function () {
            //初始化标签中的Ext:Qtip属性。
            Ext.QuickTips.init();
            Ext.form.Field.prototype.msgTarget = 'side';
            var win = new Ext.Window({
                title: '宜搜游戏运营管理后台----登录',
                width: 476,
                height: 274,
                resizable: true,
                modal: true,
                closable: true,
                maximizable: true,
                minimizable: true,
                items:[logoPanel,form]
            });
            win.show();
        });
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
var form = Ext.widget('form', {
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    border: false,
    bodyPadding: 10,
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 60,
        labelStyle: 'font-weight:bold'
    },
    items: [{
        xtype: 'textfield',
        fieldLabel: '用户名',
        afterLabelTextTpl: required,
        allowBlank: false
    }, {
        xtype: 'textfield',
        fieldLabel: '密码',
        afterLabelTextTpl: required,
        inputType:'password',
        allowBlank: false
    },{
        xtype: 'fieldcontainer',
        labelStyle: 'font-weight:bold;padding:0;',
        layout: 'hbox',
        fieldDefaults: {
            labelAlign: 'left'
        },
        items: [{
            flex: 1,
            name: 'firstName',
            xtype: 'textfield',
            width: 200,
            afterLabelTextTpl: required,
            fieldLabel: '验证码',
            allowBlank: false
        },{     
            xtype: 'box', //或者xtype: 'component',     
            width: 80, //图片宽度     
            height: 30, //图片高度     
            autoEl: {     
                tag: 'img',    //指定为img标签     
                src: 'http://118.244.198.75:8080/gameAdmin/module/common/VerifyCode.jsp'    //指定url路径     
            },
            listeners: {
                render: function(component){
                        component.getEl().on('click', function(e){
                        	component.getEl();
                        });
                }
           }
        }]
    }],
    buttonAlign: 'center',
    buttons: [{
        text: '登录',
        handler: function() {
            this.up('form').getForm().reset();
            this.up('window').hide();
        }
    }, {
        text: '重置',
        handler: function() {
            if (this.up('form').getForm().isValid()) {
                this.up('form').getForm().reset();
                this.up('window').hide();
                Ext.MessageBox.alert('Thank you!', 'Your inquiry has been sent. We will respond as soon as possible.');
            }
        }
    }]
});
var logoPanel = {
        xtype: 'panel',
        border: false,
        width: '100%',
        height: 100,
        padding: '1px',
        html: "<img src='http://w4.easou.com/index_2012_web.png' alt='软件LOGO' height='100%' width='100%'/>"
    };
