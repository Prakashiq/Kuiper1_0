var myApp = angular.module("myApp", []);

myApp.controller("logincntrl", function($scope, $window) 
{
	$scope.validate_login = function() 
	{
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.open("POST",
				"/Kuiper/common/Auth/login", true);
		
		xmlhttp.onloadend = function() {
		    if(xmlhttp.status == 404) 
		        throw new Error('login page replied 404');
		}
		
		xmlhttp.setRequestHeader("Content-type", "application/json");
		xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

		var jsondata = {
			username : $scope.user.username,
			password : $scope.user.pass
		               };

		var parameters = JSON.stringify(jsondata);

		xmlhttp.send(parameters);

		xmlhttp.onreadystatechange = function() 
		{

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var newObject = JSON.parse(xmlhttp.responseText);
				if (newObject.Status == "SUCCESS") {

					xmlhttp.onloadend = function() {
						var url = "menu.html"
						$window.location.href = url;
					};
				} else {
					 $scope.error =newObject.StatusDescription;
					alert("reponse:" + newObject.Status + ":"
							+ newObject.StatusDescription);
				}
			}
		};

	}

	$scope.cancel = function() {
		$scope.frm = {};
	}

	$scope.logoff = function() {
		var url = "index.html";
		$window.location.href = url;
	}

});

myApp.controller("blogcntrl", function($scope, $window) {
	$scope.back = function() {
		var url4 = "menu.html"
		$window.location.href = url4;
	}

	$scope.cancel = function() {
		$scope.frm1 = {};
	}

});

myApp.controller("FrmController", function($scope, $filter, $window) {
	$scope.comment = [];
	$scope.datetime = [];
	$scope.btn_add = function() {
		if ($scope.txtcomment != '')
			var date = new Date();
		$scope.ddMMMMyyyy = $filter('date')(new Date(),
				'dd, MMMM yyyy HH:mm:ss');
		$scope.comment.push($scope.ddMMMMyyyy + "   DC Associate: "
				+ $scope.txtcomment);
		$scope.txtcomment = "";
	}

	$scope.likecount = 0;
	$scope.likeClick = function($inc) {
		$scope.likecount += $inc;
	}

	$scope.btn_home = function() {
		var url = "menu.html"
		$window.location.href = url;
	}

});
