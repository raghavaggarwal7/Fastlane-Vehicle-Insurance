app.controller("signupController", function($scope,$http ,$location,$cookies) {
	//alert("inside controller");
	$scope.customerform = {};
	$scope.customerform.name=null;
	$scope.userNameList = [];
	/*$scope.passwordList = [];*/
	$scope.customerform.userName=null;
	$scope.customerform.emailId=null;
	$scope.customerform.password=null;
	$scope.customerform.confirm_password=null;
	$scope.customerform.mobileNo=null;
	$scope.customerform.submitValue=null;
	$scope.customerform.dateOfBirth=null;
	$scope.customerform.message=null;
	$scope.customerform.userNameMessage = null;
	$scope.customerform.dateMessage = null;
	$scope.customerform.userNameMessage = null;
	/*$scope.customerform.passwordMessage = null;*/
	
	$scope.validateAge = function(){
		//console.log("in validateAge")
		$scope.customerform.dateMessage = null;
		$scope.customerform.dateOfBirth = new Date($scope.customerform.dateOfBirth);
		var ageDifMs = Date.now() - $scope.customerform.dateOfBirth.getTime();
	    var ageDate = new Date(ageDifMs); // miliseconds from epoch
	    var years =  Math.abs(ageDate.getUTCFullYear() - 1970);
	    //console.log(years)
	    if(years < 18 )
	    	$scope.customerform.dateMessage = "You must be of 18 years to register ";
	    else
	    	$scope.customerform.dateMessage = null;
	}
	
	$scope.customerform.submitTheForm=function()
	{	
		//alert("in submit the form function")
		//console.log("in submit the form function");
		$scope.customerform.message=null;
		$scope.customerform.dateOfBirth = new Date($scope.customerform.dateOfBirth);
		$scope.customerform.dateOfBirth = $scope.customerform.dateOfBirth.toLocaleString();
		//alert("after dob")
		//console.log("after dob");
		//console.log("in registerUser");
		var data = angular.toJson($scope.customerform);
		//console.log(data);
		/*alert(data);*/
		
		if($scope.customerform.userNameMessage == "Username is available"){
		if($scope.customerform.dateMessage == null){
			if($scope.customerform.password==$scope.customerform.confirm_password)
			{
			if($scope.customerform.userNameMessage === "Username is available"){
				//alert("inside ifffffff");
				//console.log("inside iffffff");
    			$http.post(URI+"Insurance/signup",data).then(function(res){
    				//alert("inside post success")
    				//console.log("in success" + res.data.message);
				$scope.customerform.message=res.data.message;
				$scope.customerform = {};
				/*$location.path("/userprofile/"+$scope.customerform.userName);*/
				window.location="/Fastlane_Vehicle_Insurance/index.html#signin";
				
			},function(res){
    				//console.log(res);
			});
			}
			else{
				$scope.customerform.message = "Username is not available";
			}
			}
		else
			{
			$scope.customerform.message="Passwords are not matching";
			}
		}else{
			$scope.customerform.message = "Enter a valid date of birth"
		}
		}else{
			$scope.customerform.message = "Enter a valid username"
		}
	};
	
	$scope.checkUserName = function(){
		$scope.customerform.message = null;
		//console.log("in checkUserName");
		isValid = true;
		//console.log($scope.customerform.userName)
		isValidRegex = true;
		var data = angular.toJson($scope.customerform.userName);
		$http.get(URI+"Insurance/getusername").then(function(response) {
			//console.log("in fetch success");
			$scope.userNameList = response.data;
			//console.log($scope.userNameList);
			for (var int = 0; int < $scope.userNameList.length; int++) {
				//console.log("in for")
				if($scope.customerform.userName.match("[a-zA-Z0-9]{6,12}")){
					//console.log("in if regex")
					isValidRegex = true;
				}
				else{
					isValidRegex = false;
					//console.log('regex else')
				}
				
				if($scope.customerform.userName==="dummy819"){
					isValid = true;
					break;
				}
				
				if($scope.userNameList[int]===$scope.customerform.userName){
					isValid = false;
					break;
				}

			}
			//console.log("isValid"+isValid);
			//console.log("isValidRegex"+isValidRegex)
			if(isValid && isValidRegex)
				$scope.customerform.userNameMessage = "Username is available";
			if(!isValidRegex)
				$scope.customerform.userNameMessage = "Username must be alphanumeric with min 6 char and max 12";
			else if(!isValidRegex && !isValid)
				$scope.customerform.userNameMessage = "Username is not available";
		}, function(response) {
			console.log("in fetch error");
			$scope.customerform.message = 'Error in validating userName.Try after some time';
		});
	}
});


app.controller("LoginController", function($scope, $http, $location,$cookies) {
	$scope.loginForm = {};
	$scope.loginForm.userName = null;
	$scope.loginForm.password = null;
	$scope.loginForm.message = null;
	
	$scope.logOut = function(){
		//alert("logout invoked")
		//console.log("logout")
		$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location="/Fastlane_Vehicle_Insurance/index.html#top";
	}
	
	$scope.loginForm.loginUser = function() {
		
		//console.log("in loginUser");
		$scope.loginForm.message = null;
		var data = angular.toJson($scope.loginForm);
		$http.post(URI+"Insurance/verifyusercredentials", data).then(function(response) {
			$scope.loginForm.message = response.data.message;
			//console.log("in verifyusercredentials success");
			//console.log($scope.loginForm.message);
			//console.log("login ctrl"+$scope.loginForm.userName);
			if($scope.loginForm.message==="Passwords do not match")
				{
				$scope.loginForm.message="Password do not match";
				}
			else if($scope.loginForm.message==="User not found"){
				$scope.loginForm.message="User not found";
			}else
				{
				var exp = new Date();
				exp.setDate(exp.getDate() + 7) //user is logged in for 7 days
				//console.log(exp)
				$cookies.put("userName", $scope.loginForm.userName, {expires : exp});
				//$cookieStore.put("userName",$scope.loginForm.userName);
				window.location="/Fastlane_Vehicle_Insurance/AfterLoginMainPage.html#top";
				}
		}, function(response) {
			$scope.loginForm.message = response.data.message;
			//console.log("in verifyusercredentials error");
			//console.log($scope.loginForm.message);
		})
	}
})



app.controller("AdminLoginController", function($scope, $http, $location,$cookies) {
	$scope.adminloginForm = {};
	$scope.adminloginForm.userName = null;
	$scope.adminloginForm.password = null;
	$scope.adminloginForm.message = null;
	
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
			
			var exp = new Date();
			exp.setDate(exp.getDate() + 7) //user is logged in for 7 days
			//console.log(exp)
			//$cookies.put("userName", $scope.adminloginForm.userName, {expires : exp});
			$location.path("/adminprofile/");
		//window.location = "buyIndex.html"
				}
		}, function(response) {
			$scope.adminloginForm.message = response.data.message;
			//console.log("in verifyadmincredentials error");
			//console.log($scope.adminloginForm.message);
		})
	}
})

app.controller("AdminController", function($scope, $routeParams,$cookies){
	//alert("inside AdminController");
	//console.log("inside AdminController");

	//console.log("inside userController");
	$scope.uName = null;
	//$scope.uName=$cookies.get('userName');
	//console.log("i am admin "+ $cookies.get('userName'));
	//$scope.uName = $routeParams.userName;
	//alert($scope.uName);
	$scope.fetchUserName=function(){
		//console.log("in fetch")
		//$scope.userName = $cookies.get('userName');
			//console.log($scope.userName);
	}
	
	$scope.logOut = function(){
		//console.log("logout")
		//$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location = "index.html";
	}
	
});



ap.controller("CarDetailsController", function($scope, $http, $location,$cookies) {
	//alert("cardetails");
	//console.log("cardetails");
	
	$scope.cardetailsform = {};
	$scope.purchaseCityList=[];
	$scope.carBrandList=[];
	$scope.carModelList=[];
	
	$scope.cardetailsform.purchaseCity=null;
	$scope.cardetailsform.dateOfPurchase=null;
	$scope.cardetailsform.carBrand=null;
	$scope.cardetailsform.carModel=null;
	$scope.cardetailsform.carPrice=null;
	$scope.cardetailsform.message=null;
	$scope.cardetailsform.idv=null;
	$scope.cardetailsform.userName = null;
	$scope.cardetailsform.regId=null;
	$scope.cardetailsform.purchaseDateMessage=null;
	$scope.cardetailsform.userName=$cookies.get('userName');
	$scope.maxDate = new Date();
	
	$scope.validatePurchaseDate = function(){
		if($scope.cardetailsform.dateOfPurchase > $scope.maxDate){
			//console.log("in if");
			$scope.cardetailsform.purchaseDateMessage = "Purchase date can't be greater than today's date";
		}else
			$scope.cardetailsform.purchaseDateMessage = null;
	}
	
	$scope.fetchPurchaseCityList=function(){
		//alert("In Controller:  purchase city list");
		//console.log("maxDate- " + $scope.maxDate)
		//console.log("In Controller:  purchase city list");
		$http.get(URI+"Insurance/fetchpurchasecitylist").then(function(res){
			$scope.purchaseCityList=res.data;
			$scope.cardetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.cardetailsform.message=res.data.message;
			$scope.purchaseCityList=null;
			
			
		}
	}
	
	$scope.fetchCarBrandList=function(){
		//alert("In Controller:car brand list");
		//console.log("In Controller:car brand list");
		$http.get(URI+"Insurance/fetchcarbrandlist").then(function(res){
			$scope.carBrandList=res.data;
			$scope.cardetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.cardetailsform.message=res.data.message;
			$scope.cityList=null;
			
			
		}
	}
	
	$scope.fetchCarModelList=function(){
		//alert("In Controller: car model list");
		//console.log("In Controller:car model list");
		var data=angular.toJson($scope.cardetailsform);
		//alert(data);
		//console.log(data);
		$http.post(URI+"Insurance/fetchcarmodellist",data).then(function(res){
			$scope.carModelList=res.data;
			//console.log($scope.carModelList);
			$scope.cardetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.cardetailsform.message=res.data.message;
			$scope.carModelList=null;
			
			
		}
	}
	
	$scope.fetchCarPrice=function(){
		//alert("In Controller: car price");
		//console.log("In Controller: car price");
		if($scope.cardetailsform.carModel!=null){
		var data=angular.toJson($scope.cardetailsform);
		//alert(data);
		//console.log(data);
		$http.post(URI+"Insurance/fetchCarPrice",data).then(function(res){
			$scope.cardetailsform.carPrice=res.data[0];
			//console.log($scope.cardetailsform.carPrice);
			$scope.cardetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.cardetailsform.message=res.data.message;
			$scope.cardetailsform.carPrice=null;
			
			
		}
		}
	}
	
	$scope.cardetailsform.submitCarForm=function(){
		//alert("in submitCarForm function");
		//console.log("in submitCarForm function");
		$scope.cardetailsform.message=null;
		/*var purchaseDate=new Date;
		purchaseDate=$scope.cardetailsform.dateOfPurchase.toLocaleString();
		$scope.cardetailsform.dateOfPurchase = new Date($scope.cardetailsform.dateOfPurchase);
		$scope.cardetailsform.dateOfPurchase = $scope.cardetailsform.dateOfPurchase.toLocaleString();
		
		var today=new Date(today);                  //current date
		//today=today.toLocaleString();
		//alert(purchaseDate.getMonth());
		var day=purchaseDate.getDay;
		var month=purchaseDate.getMonth;
		var year=purchaseDate.getFullYear;
		
		var newDate=new Date(newDate);                 //for first if condition
//		newDate.setDate(day);
		newDate=newDate.toLocaleString();
//		newDate.setMonth(month+6);
		newDate.set;
		alert(newDate);*/
		if($scope.cardetailsform.purchaseDateMessage == null){
		$scope.cardetailsform.dateOfPurchase = new Date($scope.cardetailsform.dateOfPurchase);
		$scope.cardetailsform.dateOfPurchase = $scope.cardetailsform.dateOfPurchase.toLocaleString();
		
		var data = angular.toJson($scope.cardetailsform);
		//alert(data);
		
		$http.post(URI+"Insurance/postCarDetails",data).then(function(res){
			//alert("inside POST success")
			//console.log("in success" + res.data.message);
		$scope.cardetailsform.message=res.data.message;
		$scope.cardetailsform = res.data;
		//console.log($scope.cardetailsform.regId)
		/*$location.path("/userprofile/"+$scope.customerform.userName);*/
		$cookies.put('regId', $scope.cardetailsform.regId);
		/*$location.path("/userprofile/"+$scope.cardetailsform.regId);*/
		window.location="/Fastlane_Vehicle_Insurance/partials/userprofile.html?regId="+$scope.cardetailsform.regId;
		//console.log("---"+$cookies.get('regId'))
		
		
	},function(res){
			//console.log(res);
	});
		}else{
			$scope.cardetailsform.message = "Enter valid purchase date"
		}
		
		/*var today1=new Date();
		$scope.today=today1.setDate(today1.getDate);
		$scope.today=$scope.today.toLocaleString();
		var nDate=new Date();                
		$scope.newDate=nDate.setDate(nDate.getFullYear,nDate.getMonth+6,nDate.getDay);
		$scope.newDate=$scope.newDate.toLocaleString();
		
		alert($scope.newDate);
		
		var newoneYearAhead=new Date();
		$scope.oneYearAhead=newoneYearAhead.setDate($scope.cardetailsform.dateOfPurchase.getFullYear+1,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		$scope.oneYearAhead=$scope.oneYearAhead.toLocaleString();
		//oneYearAhead.setFullYear($scope.cardetailsform.dateOfPurchase.getFullYear+1,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		var twoYearAhead=new Date;
		twoYearAhead.setFullYear($scope.cardetailsform.dateOfPurchase.getFullYear+2,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		var threeYearAhead=new Date;
		threeYearAhead.setFullYear($scope.cardetailsform.dateOfPurchase.getFullYear+3,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		var fourYearAhead=new Date;
		fourYearAhead.setFullYear($scope.cardetailsform.dateOfPurchase.getFullYear+4,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		var fiveYearAhead=new Date;
		fiveYearAhead.setFullYear($scope.cardetailsform.dateOfPurchase.getFullYear+5,$scope.cardetailsform.dateOfPurchase.getMonth,$scope.cardetailsform.dateOfPurchase.getDay);
		
		if($scope.today<=$scope.newDate){
			alert("date under six months");
			$scope.cardetailsform.idv=0.95*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else if($scope.today<=$scope.oneYearAhead){
			alert("between six months and one year");
			$scope.cardetailsform.idv=0.85*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else if($scope.today<=twoYearAhead){
			alert("between 1 year and 2 years");
			$scope.cardetailsform.idv=0.8*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else if($scope.today<=threeYearAhead){
			alert("between 2 years and 3 years");
			$scope.cardetailsform.idv=0.7*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else if($scope.today<=fourYearAhead){
			alert("between 3 and 4 years");
			$scope.cardetailsform.idv=0.6*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else if($scope.today<=fiveYearAhead){
			alert("between 4 and 5 years");
			$scope.cardetailsform.idv=0.5*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}
		else{
			alert("exceeding 5 years");
			$scope.cardetailsform.idv=0.4*($scope.cardetailsform.carPrice);
			alert($scope.cardetailsform.idv);
		}*/
			
		
	}
	
});

ap.controller("UserController", function($scope, $http, $routeParams,$cookies){
	//alert("inside userController");
	//console.log("inside userController");
	$scope.uName = null;
	$scope.uName=$cookies.get('userName');
	//console.log("i am user "+ $cookies.get('userName'));
	//console.log("regId "+ $cookies.get('regId'));

	$scope.logOut = function(){
		//console.log("logout")
		$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location="/Fastlane_Vehicle_Insurance/index.html#top";
	}
	
	$scope.buyPolicy={};
	$scope.premiumList=[];
	$scope.buyPolicy.regId=$cookies.get('regId');
	$scope.buyPolicy.userName=$cookies.get('userName');
	$scope.buyPolicy.purchaseCity=null;
	$scope.buyPolicy.carBrand=null;
	$scope.buyPolicy.carModel=null;
	$scope.buyPolicy.carPrice=null;
	$scope.buyPolicy.idv=null;
	$scope.buyPolicy.dateOfPurchase=null;
	$scope.buyPolicy.finalPremium=null;
	$scope.buyPolicy.policyType=null;
	//$scope.buyPolicy.message=null;
	$scope.buyPolicy.policyNo=null;
	$scope.premium=null;
	$scope.buyPolicy.policy_date=null;
	$scope.buyPolicy.expiry_date=null;
	$scope.temp=0;
	
	$scope.buyPolicy.getPremium=function(){
		
		//alert('in premium function')
		//console.log("in premium function");
		$scope.buyPolicy.regId = parseInt($scope.buyPolicy.regId);
		var data = angular.toJson($scope.buyPolicy);
		//console.log($scope.buyPolicy.regId);
		/*$scope.buyPolicy.regId=$cookies.get('regId');*/
		
		//console.log("regId-: "+ $scope.buyPolicy.regId);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/getCarDetails',data).then(function(res){
			$scope.buyPolicy=res.data[0];
			/*alert(res.data);
			alert($scope.buyPolicy.idv);*/
			//console.log(res.data);
			//console.log($scope.buyPolicy.idv);
			var now  = new Date();
			var years =  Math.abs(now.getUTCFullYear() - 5);
			now.setFullYear(years);
			//console.log(years);
			now.setMonth(now.getMonth());
			now.setDate(now.getDate());
			//console.log(now.toLocaleString);
			//console.log($scope.buyPolicy.dateOfPurchase.toLocaleString);
			if($scope.buyPolicy.dateOfPurchase.toLocaleString>=now.toLocaleString)
				{
				//alert("inside if");
				//console.log("inside if");
				$scope.premium=Math.round((3127*$scope.buyPolicy.idv)/100000);
				}
			else
				{
				//alert("inside else");
				//console.log("inside else");
				//alert($scope.buyPolicy.idv);
				//console.log("$scope.buyPolicy.idv");
				$scope.premium=Math.round((3283*$scope.buyPolicy.idv)/100000);
				}
			
			$scope.premiumList[0]=$scope.premium;
			//console.log($scope.premiumList[0]);
			$scope.premiumList[1]=$scope.premium+2000;
			//console.log($scope.premiumList[1]);
			
		
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyPolicy.message=res.data.message;
			$scope.buyPolicy=null;
		}
		
        $scope.addPremium=function(){
        	/*alert("in add premium");
        	alert("final premium is: ");
        	alert($scope.buyPolicy.finalPremium);*/
        	//console.log("in add premium");
        	//console.log("final premium is: ");
        	//console.log($scope.buyPolicy.finalPremium);
        	if($scope.buyPolicy.finalPremium!=null){
        		//console.log("inside if")
        		$scope.temp=0;
        	$scope.buyPolicy.finalPremium = parseFloat($scope.buyPolicy.finalPremium);
        	$scope.buyPolicy.policy_date= new Date();
        	$scope.buyPolicy.policy_date = $scope.buyPolicy.policy_date.toLocaleString();
        	$scope.buyPolicy.expiry_date = new Date();
        	var months = Math.abs($scope.buyPolicy.expiry_date.getMonth()+6);
        	$scope.buyPolicy.expiry_date.setMonth(months);
        	$scope.buyPolicy.expiry_date = $scope.buyPolicy.expiry_date.toLocaleString();
        	if($scope.premiumList[0] == $scope.buyPolicy.finalPremium)
        		$scope.buyPolicy.policyType = "Basic";
        	else
        		$scope.buyPolicy.policyType = "Prime";
        	//console.log($scope.buyPolicy.policyType)
        	var data = angular.toJson($scope.buyPolicy);
    		/*alert(data);*/
        	//console.log(data)
    		$http.post(URI+'Insurance/addPremium',data).then(function(res){
    			//$scope.buyPolicy.message=res.data.message;
    			//console.log(res.data)
    			$scope.buyPolicy = res.data;/*
    			$scope.buyPolicy.policyNo=res.data;*/
    			/*alert($scope.buyPolicy.policyNo);*/
    		
    		}),function(res){
    			//alert("In Controller: get function failure ");
    			//console.log("In Controller: get function failure ");
    			$scope.buyPolicy.message=res.data.message;
    			$scope.buyPolicy=null;
    		}
        	}
        	else{
        		//console.log("inside else")
        		$scope.temp=1;
        	}
    		
        }
	
	}
});