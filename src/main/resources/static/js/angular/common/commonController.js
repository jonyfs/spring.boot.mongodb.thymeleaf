angular.module('commomController', [])

.controller('HomeController', [ '$scope', '$http', function($scope, $http) {

	$scope.welcomeMsg = "Welcome 1!";

} ])

.controller('UserController', [ '$scope', '$http', function($scope, $http) {

	$scope.welcomeMsg = "Welcome 2!";

} ]);