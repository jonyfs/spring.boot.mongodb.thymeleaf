angular.module('commomController', [])

.controller('HomeController', [ '$scope', '$http', function($scope, $http) {

	$scope.welcomeMsg = "Welcome 1!";

} ])

.controller('UserController', [ '$scope', '$http','$stateParams','$controller','CustomResource', function($scope, $http,$stateParams, $controller, CustomResource) {

	var resource = CustomResource('/api/' + 'users/:id',   {id:'@id',page:'@page',size:'@size',sort:'@sort'}, 'users');

	$scope.headers = ['#','Name','Email'];

	$scope.fields = ['id','name','email'];

	$scope.sortableFields = ['false','true','true']; 

	$scope.pageTitle = 'Client';

	$scope.pageSubTitle = 'List';

	$scope.newUrl = '/users/new'; 

	$scope.baseUrl = '/users';

	$scope.maxPages = 5;

	$controller('CrudCtrl', { $scope: $scope,$stateParams: $stateParams, CustomizedResource: resource });

} ])


.controller('PaginationCtrl', function ($scope) {

	$scope.size = 5;

	$scope.sizes = [5,10,25,50,100];

	$scope.sort = '';

	$scope.sortOrder = 'Asc';

	$scope.goTo = function(page) {
		$scope.page = page;
		$scope.load();
	};

	$scope.first = function() {
		$scope.page = 0;
		$scope.load();
	};

	$scope.next = function() {
		$scope.page = $scope.page + 1;
		$scope.load();
	};

	$scope.prev = function() {
		$scope.page = $scope.page -1;
		$scope.load();
	};

	$scope.last = function() {
		$scope.page = $scope.lastPage;
		$scope.load();
	};

	$scope.sortBy = function(column) {
		$scope.sort = column;
		$scope.sortOrder = 'Asc';
		$scope.load();
	};

	$scope.changeOrder = function() {

		if ( $scope.sortOrder == 'Asc' ){
			$scope.sortOrder = 'Desc';
		}
		else{
			$scope.sortOrder = 'Asc';	
		}

		$scope.load();
	};

	$scope.createPagination = function( data ){
		console.log("creating pagination...");
		$scope.pages = [];
		$scope.page = data.number;
		$scope.lastPage = data.totalPages - 1;
		$scope.totalElements = data.totalElements;
		$scope.numberOfElements = data.numberOfElements;

		var count = 0;
		var changePage = false;	

		while ( ($scope.page * $scope.size) >= $scope.totalElements ){
			$scope.page--;
			changePage = true;
		}	

		for (var i = $scope.page; i < data.totalPages && i < $scope.page + $scope.maxPages; i++) {
			$scope.pages.push(i);
			count++;
		}

		if ( count < $scope.maxPages ){

			i = $scope.maxPages - count;		

			while ( i > 0){
				var newPage = $scope.page - i;
				if ( newPage >= 0 ){
					$scope.pages.push( newPage );
				}
				i--;
			}
			
		}

		$scope.pages.sort(function(a, b){return a-b;});

		if ( changePage ){
			$scope.goTo($scope.page);
		}

		console.log("done.");

	};
})

.controller('CrudCtrl', function ($scope,$stateParams, $location, $controller, CustomizedResource, Modal) {


	$controller('PaginationCtrl', { $scope: $scope});

	$scope.entity = {};

	$scope.loading = false;

	var successCallback = function (e, cb) {
		$scope.load(cb);
	};

	var successPostCallback = function (e,cb) {
		// $state.go(searchState);
		$location.path($scope.baseUrl);  
		successCallback(e, cb, function () {
			$scope.entity = {};
		});
	};

	var successCreateCallback = function (e,cb) {
		Modal.success('Successfully created!');
		$location.path($scope.baseUrl);  
		successCallback(e, cb, function () {
			$scope.entity = {};
		});
	};

	var successUpdateCallback = function (e,cb) {
		Modal.success('Successfully updated!');
		$location.path($scope.baseUrl);  
		successCallback(e, cb, function () {
			$scope.entity = {};
		});
	};

	var successDeleteCallback = function (e,cb) {
		Modal.success('Successfully deleted!');
		$location.path($scope.baseUrl);  
		successCallback(e, cb, function () {
			$scope.entity = {};
		});
	};

	var errorCallback = function (e) {
		var message = e.data.message;
		var i;
		if ( e.data.fieldErrors ){
			for( i = 0; i < e.data.fieldErrors.length; i++  ){
				message += '<br>' + e.data.fieldErrors[i].field + ": " + e.data.fieldErrors[i].message;
				console.log("field " + e.data.fieldErrors[i].code + " ==> " + e.data.fieldErrors[i].field + ": " + e.data.fieldErrors[i].message);
			}
		}
		Modal.error(message);
	};

	$scope.create = function() {
		CustomizedResource.save($scope.entity, successCreateCallback, errorCallback);
	};


	$scope.update = function() {
		CustomizedResource.update({ id: $stateParams.id}, $scope.entity, successUpdateCallback, errorCallback);
	};

	$scope.delete = Modal.confirm.delete(function(entityToDelete) {
		CustomizedResource.delete({id : entityToDelete.id}, successDeleteCallback, errorCallback);
	});

	$scope.validate = function() {
		CustomizedResource.validate({ id: null},$scope.entity, successPostCallback, errorCallback);
	};

	$scope.view = function(id) {
		$location.path($scope.baseUrl + '/' + id + "/view");
	};

	$scope.reloadSize = function(newSize) {
		$scope.size = newSize;
		$scope.load();
	};

	$scope.load = function(cb) {
		$scope.loading = true;
		if ($stateParams.id) {
			$scope.entity = CustomizedResource.get({id : $stateParams.id});
			if (cb) cb();
		} else {
			CustomizedResource.query({size : $scope.size,page : $scope.page,sort : $scope.sort + ',' + $scope.sortOrder} ,function (data) {
				$scope.createPagination(data);
				$scope.entities = data.content;					
				if (cb) cb();
			});
		}
		$scope.false = true;
	};

	$scope.load();
});