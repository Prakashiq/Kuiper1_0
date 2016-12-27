var appforpo = angular.module('appforpo',['ui.grid','ui.grid.expandable','ui.grid.saveState', 'ui.grid.selection', 'ui.grid.pinning', 'ui.bootstrap','ui-notification']);



appforpo.controller('MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

 $scope.gotomain = function(){

    $window.location.href="receiving.html"
    };


  $scope.gridOptions = {
    expandableRowTemplate: 'purchase_order_expanded.html',
    enableExpandableRowHeader: false,
    expandableRowHeight: 375,
    //subGridVariable will be available in subGrid scope
    expandableRowScope: {
      subGridVariable: 'subGridScopeVariable'
	  },
    showFooter: true,
    enableSorting: true,
    multiSelect: false,
    enableFiltering: true,
	
    enableRowSelection: true,
    rowHeight: 'auto',
    enableSelectAll: false,
    enableRowHeaderSelection: false,
   selectionRowHeaderWidth: 35,
    noUnselect: true,
    enableGridMenu: true,
    onRegisterApi: function(gridApi){
      $scope.gridApi = gridApi;
    }
  }


   $scope.gridOptions.columnDefs = [
     { name: 'PoNbr' },
     { name: 'ItemNbr'},
     { name: 'TotQty',enableFiltering: false },
     { name: 'DueQty',enableFiltering: false },
     { name: 'DeliveryNo' },
     { name: 'UserID'},
     { name: 'Timestamp',enableFiltering: false},
     {
	name: 'Receive',
	displayName:' ',
	headerCellClass: 'header-cell',
	cellClass: 'center-align',
	enableCellEdit: false,
	enableSorting: false,
	enableFiltering: false,
	enableColumnMenu: false,
	width: '14%', 
    cellTemplate: "<div class=\'ui-grid-cell-contents expand-row\'>" + "<button class=\'btn btn-primary\' ng-disabled=\'isDisabled\' ng-click=\'grid.api.expandable.toggleRowExpansion(row.entity);grid.appScope.toggle = !grid.appScope.toggle\'>{{grid.appScope.buttontext}}</button>" + "</div>"
	 }
  ];



  $http.get('data7.json')
    .success(function(data) {
      $scope.gridOptions.data = data;
       //$scope.items = data.Items;
    });


   $scope.toggle = true;

    $scope.$watch('toggle', function(){
        $scope.buttontext = $scope.toggle ? 'Show' : 'Hide';
    });   $scope.toggle = true;

    $scope.$watch('toggle', function(){
        $scope.buttontext = $scope.toggle ? 'Show' : 'Hide';
    });
	
$scope.slotid = [
 {id:1,slot:'A001'},
{id:2,slot:'B001'},
{id:3,slot:'C001'},
{id:4,slot:'D001'}
];

}]);



