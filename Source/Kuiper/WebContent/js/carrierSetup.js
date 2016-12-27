var app = angular.module('appCarrSetup',['ui.grid','ui.grid.expandable','angular-confirm','ui.grid.saveState','ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize','ui-notification']);



app.controller('MainCtrl', ['$scope', '$http',  '$modal', '$log', '$window', function ($scope, $http,  $modal, $log,$window) {

  $scope.gridOptions = {};
  $scope.mySelections = {};
  $scope.gridOptions.data = 'myData';
 
  $scope.gridOptions.multiSelect=false;
  $scope.gridOptions.enableFiltering = true;
  $scope.gridOptions.onRegisterApi= function(gridApi){
      $scope.gridApi = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(rows){
    	  
    	  var mySelections = gridApi.selection.getSelectedRows();
    	  
          document.getElementById("icarrierid").value = mySelections[0].CarrierId;
    	  document.getElementById("icarrierName").value = mySelections[0].CarrierName;
          document.getElementById("icrrcost").value =  mySelections[0].Carrcost;
          document.getElementById("icntname").value = mySelections[0].ContactName;
          document.getElementById("iadd1").value =  mySelections[0].ContactName;
          document.getElementById("iadd2").value = mySelections[0].Address1;
          document.getElementById("icity").value = mySelections[0].City;
          document.getElementById("izip").value =  mySelections[0].Zip;
          document.getElementById("istate").value = mySelections[0].State; 
          
          $scope.carrid=document.getElementById("icarrierid").value;
          $scope.carrierName=document.getElementById("icarrierName").value;
          $scope.carrcost= document.getElementById("icrrcost").value;
          $scope.cntname=document.getElementById("icntname").value;
          $scope.Address1=document.getElementById("iadd1").value;
          $scope.Address2= document.getElementById("iadd2").value;
          $scope.city=document.getElementById("icity").value;
          $scope.state= document.getElementById("istate").value;
          $scope.zip=document.getElementById("izip").value;
     
        
      });
  }
  $scope.gridOptions.rowIdentity = function(row) {
    return row.id;
  };
  $scope.gridOptions.getRowIdentity = function(row) {
    return row.id;
  };



  $scope.gridOptions.columnDefs = [
     { name:'CarrierId',displayName:'CarrierId'},
	 { name:'CarrierName' ,displayName:'CarrierName' }
	
  ];
            
            

  $scope.callsPending = 0;
  var i = 0;
  $scope.refreshData = function(){
    $("body").css("cursor", "progress");
    $scope.myData = [];

      $scope.callsPending++;
      
      $http.get('/Kuiper/common/carrier/getCarrierList')
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
  
 $scope.delete_carrier= function() {
	 $scope.error = ' Trailer ' ;

	    $("body").css("cursor", "progress");

	    var xmlhttp = new XMLHttpRequest();

	    xmlhttp.open("POST",
	        "/Kuiper/common/carrier/delete", true);
	    
	    xmlhttp.onloadend = function() {
	        if(xmlhttp.status == 404) 
	            throw new Error(' replied 404');
	    }
	    
	    xmlhttp.setRequestHeader("Content-type", "application/json");
	    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

	    
	    var jsondata = {
	      CarrierId : $scope.carrid
	                   };

	    var parameters = JSON.stringify(jsondata);

	    xmlhttp.send(parameters);

	    xmlhttp.onreadystatechange = function() 
	    {

	      if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    	  
	        var newObject = JSON.parse(xmlhttp.responseText);
	        if (newObject.Status == "SUCCESS") {
	        	
	        	 $scope.refreshData();
	        	 
	            $scope.error =' Carrier ' + $scope.carrid + ' Deleted Successfully';
	            $( "div.success" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 );
	            $scope.$apply();
	            
	            document.getElementById("icarrierid").value = '';
	      	    document.getElementById("icarrierName").value = '';
	            document.getElementById("icrrcost").value = '';
	            document.getElementById("icntname").value = '';
	            document.getElementById("iadd1").value =  '';
	            document.getElementById("iadd2").value = '';
	            document.getElementById("icity").value = '';
	            document.getElementById("izip").value =  '';
	            document.getElementById("istate").value = ''; 
	               
	                $scope.carrid='';
	                $scope.carrierName='';
	                $scope.carrcost='';
	                $scope.cntname='';
	                $scope.Address1='';
	                $scope.Address2='';
	                $scope.city='';
	                $scope.state='';
	                $scope.zip='';
	               
	        } else {
	          
	        	$scope.error = 'Error: ' +newObject.StatusDescription;
	           $( "div.failure" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 ) ;
	           $scope.$apply();
	           
	        }

	        $("body").css("cursor", "default");
	      }
	    };
 }
 
 $scope.create_carrier = function() {
	   "use strict";
	   
	   $scope.error = ' Trailer ' ;

    $("body").css("cursor", "progress");

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.open("POST",
        "/Kuiper/common/carrier/load", true);
    
    xmlhttp.onloadend = function() {
        if(xmlhttp.status == 404) 
            throw new Error(' replied 404');
    }
    
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

    
    var jsondata = {
      CarrierId : $scope.carrid,
      CarrierName : $scope.carrierName,
      Carrcost :$scope.carrcost,
      ContactName :$scope.cntname,
      Address1 :$scope.Address1,
      Address2 :$scope.Address2,
      City :$scope.city,
      State :$scope.state,
      Zip :$scope.zip
                   };

    var parameters = JSON.stringify(jsondata);

    xmlhttp.send(parameters);

    xmlhttp.onreadystatechange = function() 
    {

      if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    	  
        var newObject = JSON.parse(xmlhttp.responseText);
        if (newObject.Status == "SUCCESS") {
        	
        	 $scope.refreshData();
        	 
            $scope.error =' Carrier ' + $scope.carrid + ' Create Successfully';
            $( "div.success" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 );
            $scope.$apply();
            
            document.getElementById("icarrierid").value = '';
      	  document.getElementById("icarrierName").value = '';
            document.getElementById("icrrcost").value = '';
            document.getElementById("icntname").value = '';
            document.getElementById("iadd1").value =  '';
            document.getElementById("iadd2").value = '';
            document.getElementById("icity").value = '';
            document.getElementById("izip").value =  '';
            document.getElementById("istate").value = ''; 
               
                $scope.carrid='';
                $scope.carrierName='';
                $scope.carrcost='';
                $scope.cntname='';
                $scope.Address1='';
                $scope.Address2='';
                $scope.city='';
                $scope.state='';
                $scope.zip='';
               
        } else {
          
        	$scope.error = 'Error: ' +newObject.StatusDescription;
           $( "div.failure" ).fadeIn( 800 ).delay( 2500 ).fadeOut( 400 ) ;
           $scope.$apply();
           
        }

        $("body").css("cursor", "default");
      }
    };
  }

} ]);



