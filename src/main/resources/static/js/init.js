'use strict;'

function onSignInCallback(authResult) {
	angular.element($("#wrapper")).scope().onSignInCallback(authResult);
}

var i18nUtils = {
	callback: function() {
		angular.bootstrap(document, ['demoApp']);
	},
	loadLocaleFiles: function() {	
		window.___gcfg = {
			lang: 'en',
			parsetags: 'explicit'
		};
		$.getScript('https://apis.google.com/js/client:platform.js', this.callback);
	}
};
		