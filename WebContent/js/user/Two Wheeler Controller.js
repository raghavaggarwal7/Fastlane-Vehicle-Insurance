ap.controller("BikeDetailsController", function($scope, $http, $location,$cookies) {
	//alert("bikedetails");
	//console.log("bikedetails");
	
	$scope.bikedetailsform = {};
	$scope.purchaseCityList=[];
	$scope.bikeBrandList=[];
	$scope.bikeModelList=[];
	
	$scope.bikedetailsform.purchaseCity=null;
	$scope.bikedetailsform.dateOfPurchase=null;
	$scope.bikedetailsform.bikeBrand=null;
	$scope.bikedetailsform.bikeModel=null;
	$scope.bikedetailsform.bikePrice=null;
	$scope.bikedetailsform.message=null;
	$scope.bikedetailsform.idv=null;
	$scope.bikedetailsform.userName = null;
	$scope.bikedetailsform.regId=null;
	$scope.bikedetailsform.purchaseDateMessage=null;
	$scope.bikedetailsform.userName=$cookies.get('userName');
	$scope.maxDate = new Date();
	
	$scope.validatePurchaseDate = function(){
		if($scope.bikedetailsform.dateOfPurchase > $scope.maxDate){
			//console.log("in if");
			$scope.bikedetailsform.purchaseDateMessage = "Purchase date can't be greater than today's date";
		}else
			$scope.bikedetailsform.purchaseDateMessage = null;
	}
	
	
	
	$scope.fetchBikePurchaseCityList=function(){
		//alert("In Controller:  purchase city list");
		//console.log("In Controller:  purchase city list");
		$http.get(URI+"Insurance/fetchbikepurchasecitylist").then(function(res){
			$scope.purchaseCityList=res.data;
			$scope.bikedetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.bikedetailsform.message=res.data.message;
			$scope.purchaseCityList=null;
			
			
		}
	}
	
	$scope.fetchBikeBrandList=function(){
		//alert("In Controller:car brand list");
		//console.log("In Controller:bike brand list");
		$http.get(URI+"Insurance/fetchbikeBrandlist").then(function(res){
			$scope.bikeBrandList=res.data;
			$scope.bikedetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.bikedetailsform.message=res.data.message;
			$scope.cityList=null;
			
			
		}
	}
	
	$scope.fetchBikeModelList=function(){
		//alert("In Controller: car model list");
		//console.log("In Controller:bike model list");
		var data=angular.toJson($scope.bikedetailsform);
		//alert(data);
		//console.log(data);
		$http.post(URI+"Insurance/fetchbikeModellist",data).then(function(res){
			$scope.bikeModelList=res.data;
			//console.log($scope.bikeModelList);
			$scope.bikedetailsform.message=null;
			
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.bikedetailsform.message=res.data.message;
			$scope.bikeModelList=null;
			
			
		}
	}
	
	$scope.fetchBikePrice=function(){
		//alert("In Controller: car price");
		//console.log("In Controller: bike price");
		if($scope.bikedetailsform.bikeModel != null){
			var data=angular.toJson($scope.bikedetailsform);
			//alert(data);
			//console.log(data);
			$http.post(URI+"Insurance/fetchbikePrice",data).then(function(res){
				$scope.bikedetailsform.bikePrice=res.data[0];
				//console.log($scope.bikedetailsform.bikePrice);
				$scope.bikedetailsform.message=null;
				
				
			}),function(res){
				//alert("In Controller: get function failure ");
				//console.log("In Controller: get function failure ");
				$scope.bikedetailsform.message=res.data.message;
				$scope.bikedetailsform.bikePrice=null;
				
				
			}
		}
	}
	
	$scope.bikedetailsform.submitBikeForm=function(){
		//alert("in submitCarForm function");
		//console.log("in submitBikeForm function");
		$scope.bikedetailsform.message=null;
		
		if($scope.bikedetailsform.purchaseDateMessage == null){
		$scope.bikedetailsform.dateOfPurchase = new Date($scope.bikedetailsform.dateOfPurchase);
		$scope.bikedetailsform.dateOfPurchase = $scope.bikedetailsform.dateOfPurchase.toLocaleString();
		
		
		var data = angular.toJson($scope.bikedetailsform);
		/*alert(data);*/
		
		$http.post(URI+"Insurance/postBikeDetails",data).then(function(res){
			//alert("inside POST success")
			//console.log("in success" + res.data.message);
		$scope.bikedetailsform.message=res.data.message;
		$scope.bikedetailsform = res.data;
		//console.log($scope.bikedetailsform.regId)
		/*$location.path("/userprofile/"+$scope.customerform.userName);*/
		$cookies.put('regId', $scope.bikedetailsform.regId);
		//$location.path("/Fastlane_Vehicle_Insurance/bikeuserprofile/"+$scope.bikedetailsform.regId);
		window.location="/Fastlane_Vehicle_Insurance/partials/bikeuserprofile.html?regId="+$scope.bikedetailsform.regId;
		//console.log($cookies.get('regId'))
		
		
	},function(res){
			//console.log(res);
	});
		}
	else{
		$scope.bikedetailsform.message = "Enter valid purchase date";
	}
	}
});

ap.controller("BikeUserController", function($scope, $http, $routeParams,$cookies){
	//alert("inside userController");
	//console.log("inside bikeuserController");
	$scope.uName = null;
	$scope.uName=$cookies.get('userName');
	//console.log("i am user "+ $cookies.get('userName'));
	//console.log("regId "+ $cookies.get('regId'));
	
	$scope.logOut = function(){
		//console.log("logout")
		$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location = "index.html";
	}

	
	$scope.buyBikePolicy={};
	$scope.premiumList=[];
	$scope.buyBikePolicy.regId=$cookies.get('regId');
	$scope.buyBikePolicy.userName=$cookies.get('userName');
	$scope.buyBikePolicy.purchaseCity=null;
	$scope.buyBikePolicy.bikeBrand=null;
	$scope.buyBikePolicy.bikeModel=null;
	$scope.buyBikePolicy.bikePrice=null;
	$scope.buyBikePolicy.idv=null;
	$scope.buyBikePolicy.policyType=null;
	$scope.buyBikePolicy.dateOfPurchase=null;
	$scope.buyBikePolicy.finalPremium=null;
	//$scope.buyBikePolicy.message=null;
	$scope.buyBikePolicy.policyNo=null;
	$scope.premium=null;
	$scope.buyBikePolicy.policy_date=null;
	$scope.buyBikePolicy.expiry_date=null;
	$scope.temp=0;
	$scope.buyBikePolicy.getPremium=function(){
		
		//alert('in premium function')
		//console.log("in premium function");
		$scope.buyBikePolicy.regId = parseInt($scope.buyBikePolicy.regId);
		var data = angular.toJson($scope.buyBikePolicy);
		//console.log($scope.buyBikePolicy.regId);
		/*$scope.buyBikePolicy.regId=$cookies.get('regId');*/
		
		//console.log("regId-: "+ $scope.buyBikePolicy.regId);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/getBikeDetails',data).then(function(res){
			$scope.buyBikePolicy=res.data[0];
			/*alert(res.data);
			alert($scope.buyBikePolicy.idv);*/
			//console.log(res.data);
			//console.log($scope.buyBikePolicy.idv);
			var now  = new Date();
			var years =  Math.abs(now.getUTCFullYear() - 5);
			now.setFullYear(years);
			//console.log(years);
			now.setMonth(now.getMonth());
			now.setDate(now.getDate());
			//console.log(now.toLocaleString);
			//console.log($scope.buyBikePolicy.dateOfPurchase.toLocaleString);
			if($scope.buyBikePolicy.dateOfPurchase.toLocaleString>=now.toLocaleString)
				{
				//alert("inside if");
				//console.log("inside if");
				$scope.premium=Math.round((3127*$scope.buyBikePolicy.idv)/100000);
				}
			else
				{
				//alert("inside else");
				//console.log("inside else");
				//alert($scope.buyBikePolicy.idv);
				//console.log("$scope.buyBikePolicy.idv");
				$scope.premium=Math.round((3283*$scope.buyBikePolicy.idv)/100000);
				}
			
			$scope.premiumList[0]=$scope.premium;
			//console.log($scope.premiumList[0]);
			$scope.premiumList[1]=$scope.premium+2000;
			//console.log($scope.premiumList[1]);
			
		
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyBikePolicy.message=res.data.message;
			$scope.buyBikePolicy=null;
		}
		
        $scope.addBikePremium=function(){
        	/*alert("in add premium");
        	alert("final premium is: ");
        	alert($scope.buyBikePolicy.finalPremium);*/
        	//console.log("in add premium");
        	//console.log("final premium is: ");
        	//console.log($scope.buyBikePolicy.finalPremium);
        	if($scope.buyBikePolicy.finalPremium!=null){
        		//console.log("inside if")
        		$scope.temp=0;
        	$scope.buyBikePolicy.finalPremium = parseFloat($scope.buyBikePolicy.finalPremium);
        	$scope.buyBikePolicy.finalPremium = parseFloat($scope.buyBikePolicy.finalPremium);
        	$scope.buyBikePolicy.policy_date= new Date();
        	$scope.buyBikePolicy.policy_date = $scope.buyBikePolicy.policy_date.toLocaleString();
        	$scope.buyBikePolicy.expiry_date = new Date();
        	var months = Math.abs($scope.buyBikePolicy.expiry_date.getMonth()+6);
        	$scope.buyBikePolicy.expiry_date.setMonth(months);
        	$scope.buyBikePolicy.expiry_date = $scope.buyBikePolicy.expiry_date.toLocaleString();
        	if($scope.buyBikePolicy.finalPremium == $scope.premiumList[0])
        		$scope.buyBikePolicy.policyType = "Basic";
        	else
        		$scope.buyBikePolicy.policyType = "Prime";
        	var data = angular.toJson($scope.buyBikePolicy);
    		/*alert(data);*/
        	//console.log(data)
    		$http.post(URI+'Insurance/addBikePremium',data).then(function(res){
    			//$scope.buyBikePolicy.message=res.data.message;
    			//console.log(res.data)
    			$scope.buyBikePolicy = res.data;/*
    			$scope.buyBikePolicy.policyNo=res.data;*/
    			/*alert($scope.buyBikePolicy.policyNo);*/
    		
    		}),function(res){
    			//alert("In Controller: get function failure ");
    			//console.log("In Controller: get function failure ");
    			$scope.buyBikePolicy.message=res.data.message;
    			$scope.buyBikePolicy=null;
    		}
        	}
        	else{
        		//console.log("inside else")
        		$scope.temp=1;
        	}
    		
        }
	
	}
});