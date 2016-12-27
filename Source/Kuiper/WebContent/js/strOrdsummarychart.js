var myApp = angular.module('soApp',[]);

myApp.controller('visualController2', ['$scope', '$http','$interval', function ($scope, $http) {

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
             enabled: false,
             format: '{point.name}',
             style: {color: '#232222;'}
          }
      }
   };   

    var chartData=[];
    $http.get('/Kuiper/common/Misc/getSOSumCnt')
        .then(function(response) {
          

          for(var i=0; i < response.data.length; i++){
             point = [];
             point.push(response.data[i].order_status);
             point.push(response.data[i].total);
             chartData.push(point);
          }


          var series= [{
         type: 'pie',
            name: 'Store Order Summary Count',
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