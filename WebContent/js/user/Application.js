var app = angular.module("Application", ['ngRoute','ngCookies']);
//alert("application.js invoked");
//console.log("application.js invoked");
app.config(function($routeProvider,$cookiesProvider) {
	//when(route,object)
	$routeProvider.when("/home", {
		templateUrl: "partials/home.html"
	}).when("/register",{
		templateUrl: "partials/register.html",
		controller: "signupController"
	}).when("/two", {
		templateUrl: "twowheeler.html"
	}).when("/four", {
		templateUrl: "fourwheeler.html"
		
	}).when("/login", {	
		templateUrl: "partials/login.html",
		controller: "LoginController"
	}).when("/adminprofile/:userName", {
		templateUrl: "Admin/adminprofile.html",
		controller: "AdminController"
	}).when("/adminlogin", {
		templateUrl: "partials/adminLogin.html",
		controller: "AdminLoginController"
	}).when("/", {
		templateUrl: "partials/home.html",
	}).when("/vehicleType", {
		templateUrl: "partials/vehicleType.html",
	}).otherwise({
		redirectTo: "/"
	});
	var now = new Date();
	exp = new Date(now.getDate()+7); //user is logged in for 7 days
	$cookiesProvider.expires = exp;
});

var ap = angular.module("BuyApp", ['ngRoute','ngCookies']);
ap.config(function($routeProvider,$cookiesProvider) {
	$routeProvider.when("/", {
		templateUrl: "partials/selectVehicle.html"/*,
		controller: "InsCtrl"*/
	}).when("/buy1",{
		templateUrl: "partials/buy1.html"
	}).when("/buybike",{
		templateUrl: "partials/buybike.html"
	}).when("/cardetails",{
		templateUrl: "partials/carDetails.html",
		controller: "CarDetailsController"
	}).when("/bikedetails",{
		templateUrl: "partials/bikeDetails.html",
		controller: "BikeDetailsController"
	}).when("/bikeuserprofile/:regId",{
		templateUrl: "partials/bikeuserprofile.html",
		controller: "BikeUserController"
	}).when("/RenewCarForm",{
		templateUrl: "partials/RenewCarForm.html",
		controller: "RenewCarController"
	}).when("/RenewCarMain/:policyNo",{
		templateUrl: "partials/RenewCarMain.html",
		controller: "RenewCarMainController"
	}).when("/myAccount",{
		templateUrl: "partials/userAccount.html",
		controller: "CustomerProfileController"
    }).when("/RenewCarMain1/:regId",{
		templateUrl: "partials/RenewCarMain1.html",
		controller: "RenewCarMain1Controller"
    }).when("/RenewBikeForm",{
		templateUrl: "partials/RenewBikeForm.html",
		controller: "RenewBikeController"
	}).when("/RenewBikeMain/:policyNo",{
		templateUrl: "partials/RenewBikeMain.html",
		controller: "RenewBikeMainController"
	}).when("/RenewBikeMain1/:regId",{
		templateUrl: "partials/RenewBikeMain1.html",
		controller: "RenewBikeMain1Controller"
    }).when("/userprofile/:regId",{
		templateUrl: "partials/userprofile.html",
		controller: "UserController"
});
	var now = new Date();
	exp = new Date(now.getDate()+7); //user is logged in for 7 days
	$cookiesProvider.expires = exp;
});


var ad = angular.module("Admin", ['ngRoute','ngCookies']);
ad.config(function($routeProvider,$cookiesProvider) {
	$routeProvider.when("/", {
		templateUrl: "Admin/home.html"
}).when("/twoUpdate",{
	templateUrl: "Admin/BikeUpdate.html",
}).when("/fourUpdate",{
	templateUrl: "Admin/CarUpdate.html",
});
	var now = new Date();
	exp = new Date(now.getDate()+7); //user is logged in for 7 days
	$cookiesProvider.expires = exp;
});

//alert("URI called");
//console.log("URI called");
var URI=getURI();