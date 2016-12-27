var app = angular.module('app',['ui.grid','ui.grid.expandable','angular-confirm','ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

  $scope.gridOptions = {};
  $scope.gridOptions.data = 'myData';
  $scope.gridOptions.enableColumnResizing = true;
  $scope.gridOptions.enableFiltering = true;
  $scope.gridOptions.enableGridMenu = true;
  $scope.gridOptions.showGridFooter = true;
  $scope.gridOptions.showColumnFooter = true;
  $scope.gridOptions.rowHeight ='auto';
  $scope.gridOptions.expandableRowTemplate='trailerops_expanded.html';
  $scope.gridOptions.onRegisterApi = function(gridApi){
      $scope.gridApi = gridApi;
    }
  $scope.gridOptions.multiSelect=false;
  $scope.gridOptions.enableExpandableRowHeader=true;


  $scope.gridOptions.expandableRowScope= {
      subGridVariable: 'subGridScopeVariable'
    };

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
  
$scope.deleteRow = function(){
  angular.forEach($scope.gridApi.selection.getSelectedRows(), function (data, index) {
    $scope.gridOptions.data.splice($scope.gridOptions.data.lastIndexOf(data), 1);
    $scope.callsPending--
  });
}
/*}]);


app.controller('MainCtrl1', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {
*/
    $scope.load_po = function() {
        $("body").css("cursor", "progress");
        var sdat = null;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4 && xhr.status == 200) {
        var newObject = JSON.parse(xhr.responseText);
        $scope.pos = newObject.Po_number;
         $("body").css("cursor", "default");

      }
    });

    xhr.open("GET", "/Kuiper/common/Misc/getPOList");
    xhr.send(sdat);

  }

   $scope.create_delivery = function() {
    $("body").css("cursor", "progress");

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.open("POST",
        "/Receiving/kuiper/load/loadDelivery", true);
    
    xmlhttp.onloadend = function() {
        if(xmlhttp.status == 404) 
            throw new Error(' replied 404');
    }
    
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

    var jsondata = {
      Carrier : $scope.carrnbr,
      Delivery : $scope.delivnbr,
      PoNumber :$scope.posource,
      Trailer :$scope.trlnbr,
      TruckQty :$scope.trkqty,
      VendorName :$scope.vndrname
                   };

    var parameters = JSON.stringify(jsondata);

    xmlhttp.send(parameters);

    xmlhttp.onreadystatechange = function() 
    {

      if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

        var newObject = JSON.parse(xmlhttp.responseText);
        if (newObject.Status == "SUCCESS") {
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



app.controller('notificationController', function($scope, Notification) {

   $scope.error = function(delnbr) {
       Notification.error({message:"Delivery " + delnbr + " has been deleted!!",delay:4000,positionY:'top',positionX:'left'});
  };

});



app.controller('MainCtrl_Ext', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

   $scope.update_trailer=function()
   {

    $scope.row.entity.tstatus='OPEN';
    $scope.show = 1;
      $("body").css("cursor", "progress");
          
      var xmlhttp = new XMLHttpRequest();

      xmlhttp.open("POST", "/Receiving/kuiper/receiving/updateDelivery", true);
    
      xmlhttp.onloadend = function() {
      if(xmlhttp.status == 404) 
        throw new Error(  ' replied 404');
      }
    
      xmlhttp.setRequestHeader("Content-type", "application/json");
      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

      var jsondata = {
       Delivery :$scope.row.entity.delivery.Delivery,
       PoStatus :'OPEN_TRAILER'
     };
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
              $scope.show=1;
              $(MainCtrl).$scope.refreshData();
           } else {
            $scope.show = 2;
            console.log(newObject.StatusDescription);
           }
         $("body").css("cursor", "default");
        }
      };
    }


     $scope.cancel_trailer=function()
   {

    $scope.row.entity.tstatus='OPEN';
    $scope.show = 1;
      $("body").css("cursor", "progress");
          
      var xmlhttp = new XMLHttpRequest();

      xmlhttp.open("POST", "/Receiving/kuiper/receiving/updateDelivery", true);
    
      xmlhttp.onloadend = function() {
      if(xmlhttp.status == 404) 
        throw new Error( ' replied 404');
      }
    
      xmlhttp.setRequestHeader("Content-type", "application/json");
      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

      var jsondata = {
       Delivery :$scope.row.entity.delivery.Delivery,
       PoStatus :'CREATED'
     };
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
              $scope.show=1;
              $(MainCtrl).$scope.refreshData();
           } else {
            $scope.show = 2;
            console.log(newObject.StatusDescription);
           }
         $("body").css("cursor", "default");
        }
      };
    }


     $scope.receiving_trailer=function()
     {
      window.$windowScope = $scope.row.entity;

   /*   $window.open('receiving_popup.html', '_blank','location=no,width=600,height=500,scrollbars=no,top=100,left=700,resizable = no');*/

     }
    
  }]);


app.controller('Rec_MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {


$window.onload = function() {

      $scope.parentWindow = window.opener.$windowScope;
      document.getElementById("deliveryno").value = $scope.parentWindow.delivery.Delivery;
      document.getElementById("ponbr").value = $scope.parentWindow.PurchaseOrderNbr;
};


$scope.onSelectChange = function()
{

	var selectvalue = document.getElementById("itmNbr").value;
	var itemNumber = selectvalue.substr(selectvalue.indexOf(":")+1,selectvalue.lenght);
	
	   for (i=0;i<$scope.parentWindow.PO_Line_Lst.PO_Line.length;i++)
	   {
	   		if ($scope.parentWindow.PO_Line_Lst.PO_Line[i].item_nbr == itemNumber )
	   			{
	   			   indx = i;
	   			   break;
	   			}
	   }
    
document.getElementById("totqty").value = $scope.parentWindow.PO_Line_Lst.PO_Line[indx].ordered_qty;
document.getElementById("dueqty").value = $scope.parentWindow.PO_Line_Lst.PO_Line[indx].due_qty;
};


$scope.loadItem= function()
{
    if ($scope.ItemList == null)
	{
       var itmlst=[];
       var POlist=[];
   
       POlist=angular.copy($scope.parentWindow.PO_Line_Lst.PO_Line);
   
       for (i=0;i<POlist.length;i++)
	   {
	   		if (POlist[i].due_qty >0)
	   			{
	   			itmlst.push($scope.parentWindow.PO_Line_Lst.PO_Line[i].item_nbr);
	   			//POlist.splice(i, 1);
	   			}
	   }
       
       if (itmlst.length <= 0)
	   {
	      alert ("No more item receive");
	      $scope.show=3;
	   }
       else
	   {
	      $scope.ItemList=itmlst;
	   }
	}
};

$scope.receive_trailer=function()
{
	
	var recqty = document.getElementById("rcvqty").value;
	var dueqty = document.getElementById("dueqty").value;
	
	if ( dueqty - recqty >=0  )
	{
		var idx = document.getElementById("itmNbr").selectedIndex;
		$scope.parentWindow.PO_Line_Lst.PO_Line[idx].received_qty=$scope.parentWindow.PO_Line_Lst.PO_Line[idx].received_qty + recqty;
		$scope.parentWindow.PO_Line_Lst.PO_Line[idx].due_qty = dueqty - recqty;
		
		$scope.show = 1;
		$("body").css("cursor", "progress");
       
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.open("POST", "/Receiving/kuiper/receiving/updatePO", true);
 
		xmlhttp.onloadend = function() {
			if(xmlhttp.status == 404) 
				throw new Error( ' replied 404');
		}
 
		xmlhttp.setRequestHeader("Content-type", "application/json");
		xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");


		var parameters = JSON.stringify($scope.parentWindow);
		xmlhttp.send(parameters);

		xmlhttp.onreadystatechange = function() 
		{

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var newObject = JSON.parse(xmlhttp.responseText);
				if (newObject.Status == "SUCCESS") {
					InvLog();
					if($scope.returnvalue =="SUCCESS"){
						document.getElementById("itmNbr").value = "";
					    document.getElementById("rcvqty").value ="";
						document.getElementById("dueqty").value ="";
						$scope.ItemList="";
	           
						$scope.show=1;
					} else {
						$scope.show = 2;
						console.log(newObject.StatusDescription);
					}
				}
				else
				{
					$scope.show = 4;
					console.log($scope.returnvalue);
				}
				$("body").css("cursor", "default");
			}
		}
    }
	else
	{
		alert ("Recieved quantity is more than Due quantity!")
	}
};
 
var InvLog=function()
{

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("POST", "/Receiving/kuiper/receiving/inventoryLog", true);
 
		xmlhttp.onloadend = function() {
			if(xmlhttp.status == 404) 
				throw new Error( ' replied 404');
		}
 
	      xmlhttp.setRequestHeader("Content-type", "application/json");
	      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

	  	var selectvalue = document.getElementById("itmNbr").value;
		var itemNumber = selectvalue.substr(selectvalue.indexOf(":")+1,selectvalue.lenght);
		
	      var jsondata = {
			"item_nbr": itemNumber,
			"action": "Receiving",
			"order_nbr" : document.getElementById("ponbr").value,
			"qty" : document.getElementById("rcvqty").value
	     };
	      var parameters = JSON.stringify(jsondata);
	      xmlhttp.send(parameters);
	      
	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			var newObject = JSON.parse(xmlhttp.responseText);
			if (newObject.Status == "SUCCESS") {
					$scope.returnvalue ="SUCCESS";
			} else {
				$scope.returnvalue = newObject.StatusDescription;
				console.log(newObject.StatusDescription);
			}
			$("body").css("cursor", "default");
		}
	}
};


} ]);



