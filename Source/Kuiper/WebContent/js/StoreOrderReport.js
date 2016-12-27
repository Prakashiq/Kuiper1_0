var myapp = angular.module('ReportApp', []);

myapp.controller('POReport', function ($scope, $http) {

 $scope.showData = function( ){

 $scope.curPage = 0;
 $scope.pageSize = 1;

 $scope.datalists = [];

   $scope.callsPending++;
   var i = 0;
   $http.get('/KuiperReport/kuiper/Report/StoreOrder')
     .success(function(data) {
       $scope.callsPending--;

       data.forEach(function(row){
         row.name = row.name + ' iter ' + i;
         row.id = i;
         i++;
         $scope.datalists.push(row);
       });
     })
     .error(function() {
       $scope.callsPending--
     });
     
     $scope.numberOfPages = function() {
				return Math.ceil($scope.datalists.length / $scope.pageSize);
			};
         
}
 
});

angular.module('ReportApp').filter('pagination', function()
{
 return function(input, start)
 {
  start = +start;
  return input.slice(start);
 };
});

 