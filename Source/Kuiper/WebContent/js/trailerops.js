var app = angular.module('app',['ui.grid','ui.grid.expandable','angular-confirm','ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl', ['$scope', '$http', '$interval', '$modal', '$log', '$window', function ($scope, $http, $interval, $modal, $log,$window) {

	  $scope.gridOptions = {};
	  $scope.mySelections = {};
	  $scope.gridOptions.data = 'myData';
	  $scope.gridOptions.enableColumnResizing = true;
	  $scope.gridOptions.enableFiltering = true;
	  $scope.gridOptions.multiSelect=false;
	  $scope.primaryshow = true;

	  $scope.gridOptions.onRegisterApi= function(gridApi){
		  $scope.gridApi = gridApi;
      
      gridApi.selection.on.rowSelectionChanged($scope,function(rows){
    	  
    	  
      $scope.mySelections = gridApi.selection.getSelectedRows();
      if ($scope.mySelections.length == 0)
      {
    	  $scope.primaryshow = true;
      }
      else
      {
    	  $scope.primaryshow = false;
    	  document.getElementById("mdeliveryno").value = $scope.mySelections[0].delivery.Delivery;
          
          $scope.PoNbr =  $scope.mySelections[0].PurchaseOrderNbr;
          document.getElementById("mponbr").value =$scope.PoNbr;
          
          //dueqty totqty itmsource 
          
         // RcvQty DueQty TotQty PoNbr DeliveryNo
      }
      
      
      });
  }


  $scope.gridOptions.columnDefs = [
 { name: 'delivery.Delivery',displayName:'Delivery Number'},
    { name: 'delivery.Trailer',displayName:'Trailer Number'},
    { name: 'delivery.Carrier',displayName: 'Carrier Number'},
  { name: 'delivery.TruckQty',displayName: 'Truck Qty'},
  { name: 'PurchaseOrderNbr',displayName:'Purchase Order'},
  { name: 'store_number',displayName:'Destination Store/RDC Number'},
  { name: 'po_status',displayName: 'Status'}, 
    { name: 'MustArriveDate',displayName:'Scheduled Date'}
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
	   "use strict";
	   
	   $scope.error = ' Delivery ' + $scope.delivnbr ;
	   $scope.show_msg=true;
	   $scope.showtrailerext=false;
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
        	
        	$scope.refreshData();
        	$scope.load_po();
        	$scope.primaryshow = false;
            $scope.error =' Delivery ' + $scope.delivnbr + 'Create Successfully';
            $( "div.success" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 );
            $scope.$apply();
        		
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
       Delivery :$scope.mySelections[0].delivery.Delivery,
       PoStatus :'OPEN_TRAILER'
     };
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
        	  $scope.refreshData();
        	  $scope.primaryshow = false;
        	  $scope.load_po();
              $scope.error='Trailer '+ $scope.mySelections[0].delivery.Trailer +' opened Successfuly!!';
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


   $scope.cancel_trailer=function()
   {
    	 "use strict";
    
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
       Delivery :$scope.mySelections[0].delivery.Delivery,
       PoStatus :'CREATED'
     };
      var parameters = JSON.stringify(jsondata);
      xmlhttp.send(parameters);

      xmlhttp.onreadystatechange = function() 
      {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          var newObject = JSON.parse(xmlhttp.responseText);
          if (newObject.Status == "SUCCESS") {
        	  $scope.load_po();
        	  $scope.refreshData();
        	  $scope.primaryshow = false;
        	  $scope.error='Successfully cancelled trailer' + $scope.mySelections[0].delivery.Trailer +' !!';
              $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
              $scope.$apply();
          //    $scope.primaryshow = true;
              
           } else {
        	   $scope.error ='Error'+ newObject.StatusDescription;
               $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
               $scope.$apply();
           }
         $("body").css("cursor", "default");
        }
      };
    }



$scope.onSelectChange = function()
{
	   
	   var indx=-1;

		var e = document.getElementById("mitmNbr");
		/*var value = e.options[e.selectedIndex].value;  */
		var text = e.options[e.selectedIndex].text;
		
	    for (i=0;i<$scope.mySelections[0].PO_Line_Lst.PO_Line.length;i++)
	    {
	   		if ($scope.mySelections[0].PO_Line_Lst.PO_Line[i].item_nbr == text )
	   		{
	   		   indx = i;
	   		   break;
	   		}
	    }
	    if (indx >=0)
		{
	    	$scope.TotQty= $scope.mySelections[0].PO_Line_Lst.PO_Line[indx].ordered_qty; 
	    	document.getElementById("mtotqty").value=$scope.TotQty;
			
			
			$scope.DueQty = $scope.mySelections[0].PO_Line_Lst.PO_Line[indx].due_qty;
			document.getElementById("mdueqty").value = $scope.DueQty;
		}
	    else
    	{
			document.getElementById("mtotqty").value = "";
			document.getElementById("mdueqty").value="";
			
			$scope.TotQty = "";
			$scope.DueQty ="";
		
    	}
    
//dueqty totqty itmsource 

// RcvQty DueQty TotQty PoNbr DeliveryNo
 
};


$scope.loadItem= function()
{
    if ($scope.ItemList == null)
	{
       var itmlst=[];
       var POlist=[];
   
       POlist=angular.copy($scope.mySelections[0].PO_Line_Lst.PO_Line);
   
       for (i=0;i<POlist.length;i++)
	   {
	   		if (POlist[i].due_qty >0)
	   			{
	   			itmlst.push($scope.mySelections[0].PO_Line_Lst.PO_Line[i].item_nbr);
	   			//POlist.splice(i, 1);
	   			}
	   }
       
       
       
       if (itmlst.length <= 0)
	   {
	      $scope.error ='No more item Receive!!';
          $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
	   }
       else
	   {
	      $scope.ItemList=itmlst;
	   }
	}
};

$scope.receive_trailer=function()
{
	var recqty = parseInt(document.getElementById("mrcvqty").value);
	var dueqty = parseInt(document.getElementById("mdueqty").value);
	
	if ( (dueqty - recqty) >= 0  )
	{
		var idx = document.getElementById("mitmNbr").selectedIndex-1;
		$scope.mySelections[0].PO_Line_Lst.PO_Line[idx].received_qty=parseInt($scope.mySelections[0].PO_Line_Lst.PO_Line[idx].received_qty) + recqty;
		$scope.mySelections[0].PO_Line_Lst.PO_Line[idx].due_qty = dueqty - recqty;
		
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


		var parameters = JSON.stringify($scope.mySelections[0]);
		xmlhttp.send(parameters);

		xmlhttp.onreadystatechange = function() 
		{
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				$scope.refreshData();
				$scope.load_po();
				 $scope.primaryshow = false;
				var newObject = JSON.parse(xmlhttp.responseText);
				if (newObject.Status == "SUCCESS") {
					InvLog();
					
					document.getElementById("mitmNbr").value = "";
				    document.getElementById("mrcvqty").value ="";
					document.getElementById("mdueqty").value ="";
					document.getElementById("mlpn").value ="";
					$scope.ItemList="";
					
					$scope.poperror ='Received Item!' ;
			        $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
				} else {
					console.log(newObject.StatusDescription);
					$scope.poperror ='Error '+ newObject.StatusDescription;
			        $( "div.failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
				}
	
				}
				$("body").css("cursor", "default");
			}
    }
	else
	{
		alert ("Recieved quantity is more than Due quantity!");
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

			var e = document.getElementById("mitmNbr");
			/*var value = e.options[e.selectedIndex].value;  */
			var text = e.options[e.selectedIndex].text;
		
	      var jsondata = {
			"item_nbr": text,
			"action": "Receiving",
			"order_nbr" : document.getElementById("mponbr").value,
			"qty" : document.getElementById("mrcvqty").value,
			"lpn" :document.getElementById("mlpn").value
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



