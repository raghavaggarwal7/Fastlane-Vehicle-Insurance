app.controller("AdminLoginController", function($scope, $http, $location) {
	$scope.adminloginForm = {};
	$scope.adminloginForm.userName = null;
	$scope.adminloginForm.password = null;
	$scope.adminloginForm.message = null;
	$scope.adminloginForm.claimStatus = null;
	$scope.adminloginForm.policyNo = null;
	$scope.policyList = null;
	


	$scope.adminloginForm.loginUser = function() {
		//console.log("in admin loginUser");
		$scope.adminloginForm.message = null;
		var data = angular.toJson($scope.adminloginForm);
		//alert(data);
		//console.log(data);
		$http.post(URI+"AdminInsurance/verifyadmincredentials", data).then(function(response) {
			$scope.adminloginForm.message = response.data.message;
			//console.log("in admin verifyadmincredentials success");
			//console.log($scope.adminloginForm.message);
			//console.log("login admin ctrl"+$scope.adminloginForm.userName);
			if($scope.adminloginForm.message==="Passwords do not match")
			{
				$scope.adminloginForm.message="Password do not match";
			}
			else if($scope.adminloginForm.message==="Admin not found"){
				$scope.adminloginForm.message="Admin not found";
			}else
			{
				//$cookies.put("userName",$scope.adminloginForm.userName);

				//var exp = new Date();
				//exp.setDate(exp.getDate() + 7) //user is logged in for 7 days
				//console.log(exp)
				//$cookies.put('userName', $scope.adminloginForm.userName);
				//console.log("username cookie: "+$scope.adminloginForm.userName);
				//$location.path("/adminprofile/"+$scope.adminloginForm.userName);
				window.location = "/Fastlane_Vehicle_Insurance/partials/adminprofile.html"
			}
		}, function(response) {
			$scope.adminloginForm=null;
			//$scope.adminloginForm.message = response.data.message;
			//console.log("in verifyadmincredentials error");
			//console.log($scope.adminloginForm.message);
		})
	}
	$scope.logOut = function(){
		//console.log("logout")
		//$cookies.put("userName",undefined, {path: "/Fastlane_Vehicle_Insurance/partials"});
		//console.log($cookies.get("userName"))
		window.location = "/Fastlane_Vehicle_Insurance/index.html#top";
	}
})

ad.controller("AdminController", function($scope, $http, $routeParams){
	$scope.adminloginform = {};
	$scope.adminloginform.claimStatus = null;
	$scope.adminloginform.policyNo = null; 
	$scope.policyList = null;
	$scope.usersList= null;
	$scope.userName=null;
	//alert("inside AdminController");
	//console.log("inside AdminController");

	//console.log("inside userController");
	/*$scope.uName = null;
	$scope.uName=$cookies.get('userName');*/
	//$scope.adminUserName = $cookies.get('userName',{path:"/Fastlane_Vehicle_Insurance/partials"});
	//console.log($scope.userName);
	//console.log("i am admin "+ $cookies.get('userName'));
	//$scope.uName = $routeParams.userName;
	//alert($scope.uName);
	

	$scope.logOut = function(){
		//console.log("logout")
		//$cookies.put("userName",undefined, {path: "/Fastlane_Vehicle_Insurance/partials"});
		//console.log($cookies.get("userName"))
		window.location = "/Fastlane_Vehicle_Insurance/index.html#top";
	}
	
	$scope.getPolicyList = function(){
		//console.log("in getpolicyList")
		//alert("in getPolicyList function");
		$http.get(URI+"AdminInsurance/getpolicylist").then(function(response){
			//console.log("in success-getpolicylist")
			//console.log(response.data);
			$scope.policyList = response.data;
			//console.log($scope.policyList);
		},function(response){
			//console.log("in failure-getpolicylist");
			$scope.adminloginform=null;
		})
	}

	$scope.claim = function(claimStatus, policyNo){
		//console.log("in claim");
		$scope.adminloginform.policyNo = parseInt(policyNo);
		claimStatus = parseInt(claimStatus)
		//console.log(policyNo)
		//console.log(claimStatus)
		if(claimStatus == 1)
			$scope.adminloginform.claimStatus = "ACCEPTED";
		else
			$scope.adminloginform.claimStatus = "REJECTED";
		var data = angular.toJson($scope.adminloginform);
		$http.post(URI + "AdminInsurance/updateclaimstatus", data).then(function(response) {
			//console.log("in success - claim");
			alert(response.data.message);
			$scope.adminloginform.message = response.data.message;
			//console.log($scope.viewForm.message);
			$scope.getPolicyList();
		}, function(response) {
			//console.log("in failure - claim");
			$scope.adminloginform=null;
		})
	}
	
	$scope.AdminGetAllUsers = function(){
		//console.log("in admingetallusers");
		$http.get(URI+"AdminInsurance/AdminGetAllUsers").then(function(response) {
			//console.log("in admingetallusers success");
			$scope.usersList = response.data;
			//console.log($scope.usersList);
		}, function(response) {
			//console.log("in fetch error");
			$scope.adminloginform=null;
			

		});
	}

});