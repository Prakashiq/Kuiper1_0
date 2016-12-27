var app = angular.module('app',['ui.grid','ui.grid.expandable','angular-confirm','ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl',  ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) 
{

 $scope.gotomain = function()
 {

    $window.location.href="trailer.html"
 };
	
 $scope.gotoreceivebydel = function()
 {

    $window.location.href="delivery.html"
  };	
	

  $scope.gridOptions = 
  {
    expandableRowTemplate: 'trailerops_expanded.html',
    enableExpandableRowHeader: false,
    expandableRowHeight: 220,
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
    { name: 'deliverynbr',displayName:'Delivery Number'},
    { name: 'trailernbr',displayName:'Trailer Number'},
    { name: 'carriernbr',displayName: 'Carrier Number'},
	  { name: 'truckqty',displayName: 'Truck Qty'},
	  { name: 'door',displayName: 'Door Number'},
	  { name: 'tstatus',displayName: 'Status'},	
    { name: 'sdate',displayName:'Scheduled Date'},
    { name: 'Receive',
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

  $http.get('opentrailer.txt')
  .success(function(dat) 
  {
    // $scope.gridOptions.data = dat;
  });

	$scope.toggle = true;

  $scope.$watch('toggle', function(){
     $scope.buttontext = $scope.toggle ? 'Show' : 'Hide';
    });

  $scope.deleteRow = function(){
    angular.forEach($scope.gridApi.selection.getSelectedRows(), function (dat, index) {
     // $scope.gridOptions.data.splice($scope.gridOptions.data.lastIndexOf(dat), 1);
    });
  }
}]);

  app.controller('notificationController', function($scope, Notification) 
  {

   $scope.error = function(delnbr) 
   {
      Notification.error({message:"Delivery " + delnbr + " has been deleted!!",delay:4000,positionY:'top',positionX:'left'});           
   };

    $scope.gotomain = function()
    {
       $window.location.href="trailer.html";
    };
    
    $scope.toggle = false;

    $scope.$watch('toggle', function(){
        $scope.toggleText = $scope.toggle ? 'Hide Delivery' : 'Show Delivery';
    })
  
  });


app.controller('GridController1',  ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) 
{

    $scope.load_po = function() 
    {
        $("body").css("cursor", "progress");
        var sdta = null;

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
          if (this.readyState === 4 && xhr.status == 200) 
          {
             var newObject = JSON.parse(xhr.responseText);
             $scope.pos = newObject.Po_number;
             $("body").css("cursor", "default");
          }
        });

        xhr.open("GET", "/Kuiper/common/Misc/getPOList");
        xhr.send(sdat);
    }

    $scope.create_delivery = function() 
    {
       $("body").css("cursor", "progress");
       var xmlhttp = new XMLHttpRequest();
    
       xmlhttp.open("POST",
        "/Receiving/kuiper/load/loadDelivery", true);

       xmlhttp.onloadend = function() 
       {
          if(xmlhttp.status == 404) 
            throw new Error(url + ' replied 404');
       }
    
       xmlhttp.setRequestHeader("Content-type", "application/json");
       xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

       var jsondat = {
        Carrier : $scope.carrnbr,
        Delivery : $scope.delivnbr,
        PoNumber :$scope.posource,
        Trailer :$scope.trlnbr,
        TruckQty :$scope.trkqty,
        VendorName :$scope.vndrname
        };

        var parameters = JSON.stringify(jsondat);

        xmlhttp.send(parameters);

        xmlhttp.onreadystatechange = function() 
        {
           if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
           {
              var newObject = JSON.parse(xmlhttp.responseText);
             if (newObject.Status == "SUCCESS") 
             {
               $scope.show_msg=0;
               document.getElementById("crrnbr").value = "";
               document.getElementById("deliverynbr").value = "";
               document.getElementById("ponbr").value = "";
               document.getElementById("trlrnbr").value = "";
               document.getElementById("truckqty").value = "";
               document.getElementById("vndname").value = "";
               $scope.posource=$scope.ponumber;
               $scope.carrnbr='';
               $scope.delivnbr='';
               $scope.posource='';
               $scope.trlnbr='';
               $scope.trkqty='';
               $scope.vndrname='';
               $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
              } else {
                $scope.show_msg=1;
                $scope.error =newObject.StatusDescription;
                $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
              }
             $("body").css("cursor", "default");
            }
        };
    }
  }]);



