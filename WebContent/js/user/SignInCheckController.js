app.controller("SignInCheckController", function($scope, $http,$location, $routeParams,$cookies) {
	
	
	$scope.logOut = function(){
		//console.log("logout")
		//alert("logout invoked")
		$cookies.put("userName",undefined, {path: "/Fastlane_Vehicle_Insurance"});
		//console.log($cookies.get("userName"))
		window.location="/Fastlane_Vehicle_Insurance/index.html#top";
	}
	
	
	
	
	$scope.universal = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName==null){
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin";
		}
	}
	
	
	$scope.isLoggedInBuy = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/selectVehicle.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	$scope.isLoggedInRenew = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/RenewSelectVehicle.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	$scope.isLoggedInClaim = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/userClaim.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	
	$scope.buyPolicySelectVehicleBuy = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#buy";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/buy"
		}
	}
	
	$scope.buyPolicySelectVehicleRenew = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#renew";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/renew"
		}
	}
	
	$scope.buyPolicySelectVehicleClaim = function(){
		//alert("in func")
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#/claim";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/claim"
		}
	}
})


ap.controller("SignInCheckController", function($scope, $http,$location, $routeParams,$cookies) {
	
	
	$scope.logOut = function(){
		//console.log("logout")
		//alert("logout invoked")
		$cookies.put("userName",undefined, {path: "/Fastlane_Vehicle_Insurance"});
		//console.log($cookies.get("userName"))
		window.location="/Fastlane_Vehicle_Insurance/index.html#top";
	}
	
	
	
	
	$scope.universal = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName==null){
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin";
		}
	}
	
	
	$scope.isLoggedInBuy = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/selectVehicle.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	$scope.isLoggedInRenew = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/RenewSelectVehicle.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	$scope.isLoggedInClaim = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/partials/userClaim.html";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/signin"
		}
	}
	
	$scope.buyPolicySelectVehicleBuy = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#buy";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/buy"
		}
	}
	
	$scope.buyPolicySelectVehicleRenew = function(){
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#renew";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/renew"
		}
	}
	
	$scope.buyPolicySelectVehicleClaim = function(){
		//alert("in func")
		$scope.userName = $cookies.get('userName') || null;
		if($scope.userName){
			window.location = "/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#/claim";
		}else{
			window.location = "/Fastlane_Vehicle_Insurance/index.html#/claim"
		}
	}
})