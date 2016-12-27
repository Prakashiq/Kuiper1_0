var app = angular.module('app',['ui.grid','ui.grid.expandable','angular-confirm','ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl',['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

	  $scope.gridOptions = {};
	  $scope.mySelections = {};
	  $scope.gridOptions.data = 'myData';
	  $scope.gridOptions.enableColumnResizing = true;
	  $scope.gridOptions.enableFiltering = true;
	  $scope.gridOptions.multiSelect=false;

	  $scope.gridOptions.onRegisterApi= function(gridApi){
		  $scope.gridApi = gridApi;
      
      gridApi.selection.on.rowSelectionChanged($scope,function(rows){
    	  
      $scope.mySelections = gridApi.selection.getSelectedRows();
      
   //     iitmNbr iorderedQty idueQty iloadedQty
      
      document.getElementById("istoOrdNumber").value = $scope.mySelections[0].OrderNbr;
        
      $scope.orders = $scope.mySelections[0].Orders;
      
      $scope.stoOrdNumber=document.getElementById("istoOrdNumber").value;
     
    	  
      });
  }




  $scope.gridOptions.columnDefs = [
	{ name: 'OrderNbr',displayName: 'Store Order #'},
	{ name: 'Store_Nbr',displayName: 'Store #'},
	{ name: 'StoreName',displayName:'StoreName'},
	{ name: 'order_status',displayName: 'Status'}, 
	{ name: 'ShipDate',displayName:'Ship Date'},
	{ name: 'Carrier.CarrierId',displayName:'CarrierId'},
    { name: 'Carrier.CarrierName',displayName:'CarrierName'},
    { name: 'Trailer.TrailerId',displayName:'Trailer Id'},
    { name: 'Trailer.TrailerName',displayName:'Trailer Name'}
  ];
            
            


  $scope.callsPending = 0;
  var i = 0;
  $scope.refreshData = function(){
    $("body").css("cursor", "progress");
    $scope.myData = [];

    var start = new Date();
 //   var sec = $interval(function () {
      $scope.callsPending++;
      
      $http.get('/Shipping/kuiper/shipping/getStrOrdList')
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
  

    $scope.load_strOrder = function() {
        $("body").css("cursor", "progress");
        var sdat = null;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4 && xhr.status == 200) {
        
        $scope.storOrdlist = JSON.parse(xhr.responseText);
        var newObject = JSON.parse(xhr.responseText);
        $scope.strOrdNum = newObject.storeOrderNumber;
         $("body").css("cursor", "default");

      }
    });

    xhr.open("GET", "/Kuiper/common/Misc/getCOList");
    xhr.send(sdat);

  }
    
    $scope.load_carrId = function() {
        $("body").css("cursor", "progress");
        var sdat = null;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4 && xhr.status == 200) {
        $scope.carlist = JSON.parse(xhr.responseText);
         $("body").css("cursor", "default");

      }
    });

    xhr.open("GET", "/Kuiper/common/carrier/getCarrierList");
    xhr.send(sdat);

  }

   $scope.create_load = function() {
	   "use strict";
	   
	   $scope.error = ' Delivery ' + $scope.delivnbr ;
	   $scope.show_msg=true;
	   $scope.showtrailerext=false;
    $("body").css("cursor", "progress");

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.open("POST",
        "/Shipping/kuiper/shipping/buildLoad", true);
    
    xmlhttp.onloadend = function() {
        if(xmlhttp.status == 404) 
            throw new Error(' replied 404');
    }
    
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

    var jsondata = {
		CarrierId : $scope.carrerid.CarrierId,
		StoreOrderNum : $scope.ordnbr,
		TrailerName : $scope.trlrname,
		TrailerId : $scope.trlrnbr,
		TruckQty : $scope.trkqty
    };

    var parameters = JSON.stringify(jsondata);

    xmlhttp.send(parameters);

    xmlhttp.onreadystatechange = function() 
    {

      if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    	  
        var newObject = JSON.parse(xmlhttp.responseText);
        if (newObject.Status == "SUCCESS") {
        	$scope.refreshData();
	        $scope.error =' Trailer is ready for Load!!';
	        $( "div.success" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 );
	        $scope.$apply();
        		
           document.getElementById("icarrerid").value = "";
           document.getElementById("iordnbr").value = "";
           document.getElementById("itrlrname").value = "";
           document.getElementById("itrlrnbr").value = "";
           document.getElementById("itrkqty").value = "";
               
	   		 $scope.carrerid='';
			 $scope.ordnbr='';
			 $scope.trlrname='';
			 $scope.trlrnbr='';
			 $scope.trkqty='';
			 
	           
        } else {
          
        	$scope.error = 'Error: ' +newObject.StatusDescription;
           $( "div.failure" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 ) ;
           $scope.$apply();
           
        }

        $("body").css("cursor", "default");
      }
    };
  }

   $scope.update_trailer=function()
   {
	   "use strict";
	   $scope.error='$scope.mySelections[0].OrderNbr';
       $("body").css("cursor", "progress");
          
      var xmlhttp = new XMLHttpRequest();

      xmlhttp.open("POST", "/Shipping/kuiper/shipping/updateTrailer", true);
    
      xmlhttp.onloadend = function() {
      if(xmlhttp.status == 404) 
        throw new Error(  ' replied 404');
      }
    
      xmlhttp.setRequestHeader("Content-type", "application/json");
      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

      var jsondata = {
    		  OrderNumber :$scope.mySelections[0].OrderNbr,
    		  OrderStatus :'OPEN_TRAILER'
      };
      
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
        	  $scope.refreshData();
              $scope.error='Trailer '+ $scope.mySelections[0].trailernbr +' opened Successfuly!!';
              $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
              $scope.$apply();
           } else {
            $scope.error = 'Error' + newObject.StatusDescription;
            $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
            $scope.$apply();
           }
         $("body").css("cursor", "default");
        }
      };
    }


     $scope.cancel_load=function()
   {
    	 "use strict";

      $("body").css("cursor", "progress");
          
      var xmlhttp = new XMLHttpRequest();

      xmlhttp.open("POST", "/Shipping/kuiper/shipping/updateTrailer", true);
    
      xmlhttp.onloadend = function() {
      if(xmlhttp.status == 404) 
        throw new Error( ' replied 404');
      }
    
      xmlhttp.setRequestHeader("Content-type", "application/json");
      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
     // var obj = JSON.stringify($scope.mySelections);
      var jsondata = {
    		  OrderNumber :$scope.mySelections[0].OrderNbr,
    		  OrderStatus :'CREATED'
     };
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
        	  $scope.refreshData();
        	  $scope.error='Successfully cancelled trailer' + $scope.mySelections[0].Trailer.TrailerId +' !!';
              $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
              $scope.$apply();
           } else {
        	   $scope.error ='Error'+ newObject.StatusDescription;
               $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
               $scope.$apply();
           }
         $("body").css("cursor", "default");
        }
      };
    }



$scope.loadItem= function()
{
    /*if ($scope.ItemList == null)
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
	} */
};

$scope.onSelectChange = function()
{
	var indx=-1;

	var e = document.getElementById("iitmNbr");
	/*var value = e.options[e.selectedIndex].value;  */
	var text = e.options[e.selectedIndex].text;
	
	   for (i=0;i<$scope.orders.Order_Line.length;i++)
	   {
	   		if ($scope.orders.Order_Line[i].item_nbr == text )
	   			{
	   			   indx = i;
	   			   break;
	   			}
	   }
    if (indx != -1)
    	{
    		document.getElementById("iorderedQty").value = $scope.orders.Order_Line[indx].ordered_qty;
			document.getElementById("idueQty").value = $scope.orders.Order_Line[indx].due_qty;

			$scope.orderedQty=document.getElementById("iorderedQty").value;
			$scope.dueQty=document.getElementById("idueQty").value;
    	}
    else
    	{
	    	document.getElementById("iorderedQty").value = "";
	    	document.getElementById("idueQty").value = "";
	
	    	$scope.orderedQty="";
	    	$scope.dueQty="";
    	
    	}
};


$scope.receive_trailer=function()
{
	
	var loadedqty = document.getElementById("iloadedQty").value;
	var dueqty = document.getElementById("idueQty").value;
	
	if ( dueqty - loadedqty >=0  )
	{
		var idx = (document.getElementById("iitmNbr").selectedIndex)-1;
		if (idx < 0)
		{
			alert ("Choose an item to Load!")
		}
		else
		{
			$scope.orders.Order_Line[idx].fulfilled_qty = parseInt($scope.orders.Order_Line[idx].fulfilled_qty) + loadedqty;
			$scope.orders.Order_Line[idx].due_qty = dueqty - loadedqty;
			
			$scope.show = 1;
			$("body").css("cursor", "progress");
	       
			var xmlhttp = new XMLHttpRequest();
	
			xmlhttp.open("POST", "/Shipping/kuiper/shipping/updateSO", true);
	 
			xmlhttp.onloadend = function() {
				if(xmlhttp.status == 404) 
					throw new Error( ' replied 404');
			}
	 
			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
	
		
			var parameters = JSON.stringify($scope.mySelections[0]);
			xmlhttp.send(parameters);
	
			xmlhttp.onreadystatechange = function() 
			{
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	
					var newObject = JSON.parse(xmlhttp.responseText);
					if (newObject.Status == "SUCCESS" ) {
						$scope.refreshData();
						
							InvLog();
							document.getElementById("iitmNbr").value = "";
						    document.getElementById("iloadedQty").value ="";
							document.getElementById("idueQty").value ="";
							$scope.ItemList="";
		           
							$scope.poperror ='Loaded Sucessful!' ;
					        $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
						
					}
					else
					{
						$scope.show = 4;
						console.log($scope.returnvalue);
						$scope.poperror ='Error '+ newObject.StatusDescription;
				        $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
					
					}
					$("body").css("cursor", "default");
				}
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

	xmlhttp.open("POST", "/Shipping/kuiper/shipping/inventoryLog", true);
 
		xmlhttp.onloadend = function() {
			if(xmlhttp.status == 404) 
				throw new Error( ' replied 404');
		}
 
	      xmlhttp.setRequestHeader("Content-type", "application/json");
	      xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

//	  	var selectvalue = document.getElementById("itmNbr").value;
//		var itemNumber = selectvalue.substr(selectvalue.indexOf(":")+1,selectvalue.lenght);
		
		var e = document.getElementById("iitmNbr");
		/*var value = e.options[e.selectedIndex].value;  */
		var text = e.options[e.selectedIndex].text;
	
	      var jsondata = {
			"item_nbr": text,
			"action": "Loaded",
			"order_nbr" : document.getElementById("istoOrdNumber").value,
			"qty" : document.getElementById("iloadedQty").value
	     };
	      var parameters = JSON.stringify(jsondata);
	      xmlhttp.send(parameters);
	      
	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			var newObject = JSON.parse(xmlhttp.responseText);
			if (newObject.Status == "SUCCESS") {
					$scope.returnvalue ="SUCCESS";

					$scope.poperror ='Inventory updated Sucessful!' ;
			        $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
				} else {
					$scope.returnvalue =newObject.StatusDescription;
					$scope.poperror ='Error '+ newObject.StatusDescription;
			        $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
				}
			$("body").css("cursor", "default");
		}
	}
};


} ]);



