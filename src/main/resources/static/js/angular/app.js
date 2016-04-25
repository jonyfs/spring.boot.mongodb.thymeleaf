var appPath = location.pathname;

angular.module('app', [
	'ngResource',
	'ngRoute',
	'Commom.Route',
	'Commom.Directives',
	'Commom.Factory',
	'autoActive'])

	.config(['$httpProvider', function($httpProvider) {

        $httpProvider.interceptors.push('securityInterceptor');
        
	}]);