var myApp = angular.module('myApp',[]);

myApp.controller('visualController', ['$scope', '$http','$interval', function ($scope, $http) {

   var chart = {      
      type: 'pie',     
      options3d: {
         enabled: true,
         alpha: 0,
         beta: 5      }
   };
   var title = {
		   style: {
		         color: '#232222;',
		         font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
		      },
      text: ''   
   };   
   var tooltip = {
      pointFormat: '{point.name}: <b>{point.percentage:.1f}%'
   };

   var plotOptions = {
      pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          depth: 35,
          dataLabels: {
             enabled: true,
             format: '{point.name}',
             style: {color: '#232222;'}
          }
      },
      credits: {
          enabled: false
      },
   };   

    var chartData=[];
    $http.get('/Kuiper/common/Misc/getPOSumCnt')
        .then(function(response) {
          

          for(var i=0; i < response.data.length; i++){
             point = [];
             point.push(response.data[i].po_status);
             point.push(response.data[i].total);
             chartData.push(point);
          }


          var series= [{
         type: 'pie',
            name: 'Po Summary Count',
            data: chartData
   }];  

          
             json = {};   
             json.chart = chart; 
             json.title = title;       
             json.tooltip = tooltip; 
             json.plotOptions = plotOptions; 
             json.series = series;   
             $('#container').highcharts(json);
        })
        .catch(function(response) {
  console.error('error', response.status, response.data);
});

 }]);