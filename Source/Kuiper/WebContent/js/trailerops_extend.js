var myapp = angular.module('TraileExtendApp', []);

myapp.controller('TraileExtendCtrl', [ '$scope', '$http',
		function($scope, $http) {

			$scope.update_trailer = function() {
					alert ("update");
			}

			$scope.cancel_trailer = function() {
				alert ("Cancel");
			}

			$scope.receiving_trailer = function() {
				alert ("receive");
			}

		} ]);
