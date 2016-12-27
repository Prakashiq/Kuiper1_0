var appforpo = angular.module('itmRpt',['ui.grid','ui.bootstrap']);



appforpo.controller('MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

	 $scope.gridOptions = {};
	  $scope.gridOptions.data = 'myData';
	  $scope.gridOptions.enableColumnResizing = false
	  $scope.gridOptions.enableFiltering = true;
	  $scope.gridOptions.enableGridMenu = true;
	  $scope.gridOptions.showGridFooter = false;
	  $scope.gridOptions.showColumnFooter = false;
	  $scope.gridOptions.rowHeight ='auto';
	  $scope.gridOptions.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	    }
	  $scope.gridOptions.multiSelect=false;
	  $scope.gridOptions.enableExpandableRowHeader=false;




	  $scope.gridOptions.rowIdentity = function(row) {
	    return row.id;
	  };
	  $scope.gridOptions.getRowIdentity = function(row) {
	    return row.id;
	  };


	  $scope.gridOptions.columnDefs = [
	 { name: 'delivery.Delivery',displayName:'Delivery Number'},
	    { name: 'delivery.Trailer',displayName:'Trailer Number'},
	    { name: 'delivery.Carrier',displayName: 'Carrier Number'},
	  { name: 'delivery.TruckQty',displayName: 'Truck Qty'},
	  { name: 'PurchaseOrderNbr',displayName:'Purchase Order'},
	  { name: 'po_status',displayName: 'Status'}, 
	    { name: 'MustArriveDate',displayName:'Scheduled Date'},
	  ];
	            
	            

	  $scope.callsPending = 0;
	  var i = 0;
	  $scope.refreshData = function(){
	    $("body").css("cursor", "progress");
	    $scope.myData = [];

	    var start = new Date();
	 //   var sec = $interval(function () {
	      $scope.callsPending++;
	      
	      $http.get('/Receiving/kuiper/receiving/getPOList')
	        .success(function(data) {
	          $scope.callsPending--;

	          data.forEach(function(row){
	            row.name = row.name + ' iter ' + i;
	            row.id = i;
	            i++;
	            $scope.myData.push(row);
	          });
	        })
	        .error(function() {
	          $scope.callsPending--
	        });
	        $("body").css("cursor", "default");
	 };   
}]);