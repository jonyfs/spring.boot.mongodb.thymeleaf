angular.module('commomController', [])

.controller('HomeController', [ '$scope', '$http', function($scope, $http) {

	$scope.welcomeMsg = "Welcome!";

} ])

.controller('UserController', [ '$scope', '$http', function($scope, $http) {

	$scope.welcomeMsg = "Welcome!";

} ]);