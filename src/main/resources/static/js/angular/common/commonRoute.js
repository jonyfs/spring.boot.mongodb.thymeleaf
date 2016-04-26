angular.module('Commom.Route', [
	'ngResource',
	'ngRoute',
	'ui.router',
	'commomController'])
                               
	.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
			.when('/', {
				templateUrl : 'templates/home.html', 
				controller : 'HomeController'
			});
		$routeProvider
		.when('/users', {
			templateUrl : 'templates/list.html', 
			controller : 'UserController'
		});
	}]);