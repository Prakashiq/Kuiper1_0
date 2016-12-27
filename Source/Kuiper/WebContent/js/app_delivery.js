var app = angular.module('app',['ui.grid','ui.grid.expandable', 'ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

 $scope.gotomain = function(){

    $window.location.href="trailer.html"
    };

  $scope.gridOptions = {
    expandableRowTemplate: 'delivery_expanded.html',
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
    enableSelectAll: false,
    rowHeight: 'auto',
    enableRowHeaderSelection: false,
    selectionRowHeaderWidth: 35,
    noUnselect: true,
    enableGridMenu: true,
    onRegisterApi: function(gridApi){
      $scope.gridApi = gridApi;
    },
  }

  $scope.gridOptions.columnDefs = [
    { name: 'DeliveryNo',displayName:'Delivery Number'},
    { name: 'DoorNo',displayName:'Door Number'},
    { name: 'TruckQty',displayName: 'Truck Qty'},
    { name: 'UserID',displayName:'User ID'},
	{ name:'Timestamp',displayName:'TimeStamp'},
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
     },

  ];
$http.get('data1.json')
  .success(function(data) {
   $scope.gridOptions.data = data;
   });

   $scope.toggle = true;

    $scope.$watch('toggle', function(){
        $scope.buttontext = $scope.toggle ? 'Show' : 'Hide';
    });
	
$http.get('data6.json')
 .success(function(result) {
    //$scope.Pos = result.Pos;
    $scope.pos = result;
    $scope.items = result.Items;
});

	
$scope.slotid = [
 {id:1,slot:'A001'},
{id:2,slot:'B001'},
{id:3,slot:'C001'},
{id:4,slot:'D001'}
];


}]);


