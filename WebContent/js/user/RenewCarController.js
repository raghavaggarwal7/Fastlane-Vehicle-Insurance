ap.controller("RenewCarController", function($scope, $http, $location,$cookies) {
	$scope.renewCarForm = {};
	//$scope.policyNoList=[];
	$scope.renewCarForm.userName=$cookies.get('userName');
	$scope.renewCarForm.policyNo = null;
	$scope.renewCarForm.message=null;
		
	$scope.renewCarForm.submit = function() {
		//console.log("in renewCarForm submit function");
		$scope.renewCarForm.policyNo = parseInt($scope.renewCarForm.policyNo);
		//console.log("policy number: "+$scope.renewCarForm.policyNo);
		//console.log("userName: "+$scope.renewCarForm.userName);
		isValid = false;
		var data = angular.toJson($scope.renewCarForm);
		$http.post(URI+"Insurance/verifyPolicyNo",data).then(function(response) {
			//console.log("in fetch success");
			//console.log(response.data)
			$scope.renewCarForm.message = response.data.message;
			//console.log($scope.renewCarForm.message);
			
			if($scope.renewCarForm.message=="Invalid Policy Number! Please enter a valid Policy Number."){
				//console.log("isValid false")
				$scope.renewCarForm.message = "Invalid Policy Number! Please enter a valid Policy Number.";
			}
			else{
				//console.log("isValid true");
				$cookies.put('policyNo', $scope.renewCarForm.policyNo);
				//$location.path("/RenewCarMain/"+$scope.renewCarForm.policyNo);
				window.location="/Fastlane_Vehicle_Insurance/partials/RenewCarMain.html";
			}
		}, function(response) {
			//console.log("in fetch error");
			$scope.renewCarForm.message = 'Error in validating Policy Number. Try after some time';
		
	
	});
	};
});

ap.controller("RenewCarMainController",function($scope, $http,$routeParams, $location, $cookies){
	//alert("in renewcarmaincontoller");
	$scope.renewCarMainForm = {};
	$scope.renewCarMainForm.policyNo =$cookies.get('policyNo');
	$scope.renewCarMainForm.userName=null;
	$scope.renewCarMainForm.purchaseCity=null;
	$scope.renewCarMainForm.carBrand=null;
	$scope.renewCarMainForm.carModel=null;
	$scope.renewCarMainForm.carPrice=null;
	$scope.renewCarMainForm.idv=null;
	$scope.renewCarMainForm.dateOfPurchase=null;
	$scope.renewCarMainForm.finalPremium=null;
	$scope.renewCarMainForm.policy_date=null;
	$scope.renewCarMainForm.expiry_date=null;
	$scope.message=null;
	
	$scope.renewCarMainForm.getUserCarDetails = function(){
		//alert("in get user car details");
		$scope.policyExpired=false;
		$scope.renewCarMainForm.policyNo = parseInt($scope.renewCarMainForm.policyNo);
		var data = angular.toJson($scope.renewCarMainForm);
			
		//console.log("PolicyNo-: "+ $scope.renewCarMainForm.policyNo);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/RenewCar',data).then(function(res){
			//console.log("In Controller: get function success ");
			$scope.renewCarMainForm=res.data[0];
			//alert(res.data);
			//alert($scope.renewCarMainForm.idv);
			//console.log(res.data);
			//console.log("Expiry Date: "+$scope.renewCarMainForm.expiry_date);
			
			var today = new Date();
			//console.log("today: "+today);
			if($scope.renewCarMainForm.expiry_date<=today){
				$scope.message="Your Policy has expired."
			}
			if($scope.renewCarMainForm.claimStatus=="In processing"){
				$scope.message="You have applied a claim request for this policy. STATUS: In processing. Cannot renew it."
			}
	}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			$scope.renewCarMainForm.message=res.data.message;
			$scope.renewCarMainForm=null;
		};
	};
	
	$scope.renew = function(){
		//alert("inside renew function");
		//console.log("inside renew function");
		$cookies.put('regId', $scope.renewCarMainForm.regId);
		//$location.path("/RenewCarMain1/"+$scope.renewCarMainForm.regId);
		window.location="/Fastlane_Vehicle_Insurance/partials/RenewCarMain1.html";
	}
})

ap.controller("RenewCarMain1Controller",function($scope, $http,$routeParams, $location, $cookies){
	
	//console.log("inside RenewCarMain1Controller");
	
	$scope.logOut = function(){
		//console.log("logout")
		$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location = "index.html";
	}
	
	$scope.renewCarMain1Form = {};
	$scope.renewCarMain1Form.regId = $cookies.get('regId');
	$scope.premiumList=[];
	$scope.renewCarMain1Form.idv=null;
	$scope.renewCarMain1Form.dateOfPurchase=null;
	$scope.renewCarMain1Form.finalPremium=null;
	//$scope.buyPolicy.message=null;
	$scope.renewCarMain1Form.policyType=null;
	$scope.renewCarMain1Form.policyNo=null;
	$scope.premium=null;
	$scope.renewCarMain1Form.policy_date=null;
	$scope.renewCarMain1Form.expiry_date=null;
	$scope.temp=null;
	
	$scope.renewCarMain1Form.getPremium=function(){
		
		//alert('in premium function')
		//console.log("in premium function");
		$scope.renewCarMain1Form.regId = parseInt($scope.renewCarMain1Form.regId);
		var data = angular.toJson($scope.renewCarMain1Form);
		
		/*$scope.buyPolicy.regId=$cookies.get('regId');*/
		
		//console.log("regId-: "+ $scope.renewCarMain1Form.regId);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/getCarDetails',data).then(function(res){
			$scope.renewCarMain1Form=res.data[0];
			$scope.renewCarMain1Form.finalPremium=null;
			/*alert(res.data);
			alert($scope.buyPolicy.idv);*/
			//console.log(res.data);
			//console.log($scope.renewCarMain1Form.idv);
			var now  = new Date();
			var years =  Math.abs(now.getUTCFullYear() - 5);
			now.setFullYear(years);
			//console.log(years);
			now.setMonth(now.getMonth());
			now.setDate(now.getDate());
			//console.log(now.toLocaleString);
			//console.log($scope.renewCarMain1Form.dateOfPurchase.toLocaleString);
			if($scope.renewCarMain1Form.dateOfPurchase.toLocaleString>=now.toLocaleString)
				{
				//alert("inside if");
				//console.log("inside if");
				$scope.premium=Math.round((3127*$scope.renewCarMain1Form.idv)/100000);
				}
			else
				{
				//alert("inside else");
				//console.log("inside else");
				//alert($scope.buyPolicy.idv);
				//console.log("$scope.renewCarMain1Form.idv");
				$scope.premium=Math.round((3283*$scope.renewCarMain1Form.idv)/100000);
				}
			
			$scope.premiumList[0]=$scope.premium;
			//console.log($scope.premiumList[0]);
			$scope.premiumList[1]=$scope.premium+2000;
			//console.log($scope.premiumList[1]);
			
		
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyPolicy.message=res.data.message;
			$scope.renewCarMain1Form=null;
		}
		
        $scope.addPremium=function(){
        	/*alert("in add premium");
        	alert("final premium is: ");
        	alert($scope.buyPolicy.finalPremium);*/
        	
        	//console.log("in add premium");
        	//console.log("final premium is: ");
        	//console.log($scope.renewCarMain1Form.finalPremium);
        	if($scope.renewCarMain1Form.finalPremium!=null){
        		$scope.temp=1;
        		if($scope.renewCarMain1Form.finalPremium == $scope.premiumList[0])
        			$scope.renewCarMain1Form.policyType = "Basic";
        		else
        			$scope.renewCarMain1Form.policyType = "Prime";
        	$scope.renewCarMain1Form.finalPremium = parseFloat($scope.renewCarMain1Form.finalPremium);
        	$scope.renewCarMain1Form.policy_date= new Date();
        	$scope.renewCarMain1Form.policy_date = $scope.renewCarMain1Form.policy_date.toLocaleString();
        	$scope.renewCarMain1Form.expiry_date = new Date();
        	var months = Math.abs($scope.renewCarMain1Form.expiry_date.getMonth()+6);
        	$scope.renewCarMain1Form.expiry_date.setMonth(months);
        	$scope.renewCarMain1Form.expiry_date = $scope.renewCarMain1Form.expiry_date.toLocaleString();
        	var data = angular.toJson($scope.renewCarMain1Form);
    		/*alert(data);*/
        	//console.log(data)
    		$http.post(URI+'Insurance/addPremium',data).then(function(res){
    			//$scope.buyPolicy.message=res.data.message;
    			//console.log(res.data)
    			$scope.renewCarMain1Form = res.data;/*
    			$scope.buyPolicy.policyNo=res.data;*/
    			/*alert($scope.renewCarMain1Form.policyNo);
*/    		
    		}),function(res){
    			//alert("In Controller: get function failure ");
    			//console.log("In Controller: get function failure ");
    			$scope.renewCarMain1Form.message=res.data.message;
    			$scope.renewCarMain1Form=null;
    		}
        	}
        	else{
        		$scope.temp=2;
        		//console.log("inside else")
        	}
    		
        }
	
	}
});