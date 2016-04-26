angular.module('Commom.Factory', [])

	.factory('securityInterceptor', ['$log', '$q', function($log, $q) {
		return {
			request: function(config) {
				return config || $q.when(config);
			},
			
			requestError: function(rejection) {
				return $q.reject(rejection);
			},
			
			response: function(response) {
				return response || $q.when(response);
			},
			
			responseError: function(rejection) {
				if (rejection.status == 403) {
					window.location = "./";
					return;
				}
				
				return $q.reject(rejection);
			}
		};
	}])
	
	.factory('CustomResource', function($resource){
	  return function (url, params, resourceType){
		    return $resource(
		      url, 
		      params,
		      {'get':    {method:'GET'},
		       'query':  {method:'GET', isArray:false},
		       'update':   {method:'PUT'},
		       'delete':   {method:'DELETE'},
		       'validate':   {method:'POST'},
		      }
		    );
		  };
		})
		
.factory('pagination', function ($scope) {
    function goto(page) {
      $scope.page = page;
      $scope.load();
    }

    function first() {
      $scope.page = 0;
      $scope.load();
    }

    function next() {
      $scope.page = $scope.page + 1;
      $scope.load();
    }

    function prev() {
      $scope.page = $scope.page -1;
      $scope.load();
    }

    function last() {
      $scope.page = $scope.lastPage;
      $scope.load();
    }

    // Public API here
    return {
      goto: function (page) {
        return goto(page);
      }
    };
  });		