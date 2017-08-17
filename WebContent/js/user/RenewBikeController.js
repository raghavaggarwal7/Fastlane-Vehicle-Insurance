ap.controller("RenewBikeController", function($scope, $http, $location,$cookies) {
	$scope.renewBikeForm = {};
	//$scope.policyNoList=[];
	$scope.renewBikeForm.userName=$cookies.get('userName');
	$scope.renewBikeForm.policyNo = null;
	$scope.renewBikeForm.message=null;
		
	$scope.renewBikeForm.submit = function() {
		//console.log("in renewBikeForm submit function");
		$scope.renewBikeForm.policyNo = parseInt($scope.renewBikeForm.policyNo);
		//console.log("policy number: "+$scope.renewBikeForm.policyNo);
		//console.log("userName: "+$scope.renewBikeForm.userName);
		isValid = false;
		var data = angular.toJson($scope.renewBikeForm);
		$http.post(URI+"Insurance/verifyBikePolicyNo",data).then(function(response) {
			//console.log("in fetch success");
			$scope.renewBikeForm.message = response.data.message;
			//console.log($scope.renewBikeForm.message);
			
			if($scope.renewBikeForm.message=="Invalid Policy Number! Please enter a valid Policy Number."){
				//console.log("isValid false")
				$scope.renewBikeForm.message = "Invalid Policy Number! Please enter a valid Policy Number.";
			}
			else{
				//console.log("isValid true");
				$cookies.put('policyNo', $scope.renewBikeForm.policyNo);
				//$location.path("/RenewBikeMain/"+$scope.renewBikeForm.policyNo);
				window.location="/Fastlane_Vehicle_Insurance/partials/RenewBikeMain.html";
			}
		}, function(response) {
			//console.log("in fetch error");
			$scope.renewBikeForm.message = 'Error in validating Policy Number. Try after some time';
		
	
	});
	};
});

ap.controller("RenewBikeMainController",function($scope, $http,$routeParams, $location, $cookies){
	$scope.renewBikeMainForm = {};
	$scope.renewBikeMainForm.policyNo = $cookies.get('policyNo');
	$scope.renewBikeMainForm.userName=null;
	$scope.renewBikeMainForm.purchaseCity=null;
	$scope.renewBikeMainForm.bikeBrand=null;
	$scope.renewBikeMainForm.bikeModel=null;
	$scope.renewBikeMainForm.bikePrice=null;
	$scope.renewBikeMainForm.idv=null;
	$scope.renewBikeMainForm.dateOfPurchase=null;
	$scope.renewBikeMainForm.finalPremium=null;
	$scope.renewBikeMainForm.policy_date=null;
	$scope.renewBikeMainForm.expiry_date=null;
	$scope.message=null;
	
	$scope.renewBikeMainForm.getUserBikeDetails = function(){
		$scope.policyExpired=false;
		$scope.renewBikeMainForm.policyNo = parseInt($scope.renewBikeMainForm.policyNo);
		var data = angular.toJson($scope.renewBikeMainForm);
			
		//console.log("PolicyNo-: "+ $scope.renewBikeMainForm.policyNo);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/RenewBike',data).then(function(res){
			$scope.renewBikeMainForm=res.data[0];
			/*alert(res.data);
			alert($scope.buyPolicy.idv);*/
			//console.log(res.data);
			//console.log("Expiry Date: "+$scope.renewBikeMainForm.expiry_date);
			
			var today = new Date();
			//console.log("today: "+today);
			if($scope.renewBikeMainForm.expiry_date<=today){
				$scope.message="Your Policy has expired."
			}
			if($scope.renewBikeMainForm.claimStatus=="In processing"){
				$scope.message="You have applied a claim request for this policy. STATUS: In processing. Cannot renew it."
			}
	}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyPolicy.message=res.data.message;
			$scope.renewBikeMainForm=null;
		};
	};
	
	$scope.renew = function(){
		/*alert("inside renew function");*/
		//console.log("inside renew function");
		$cookies.put('regId', $scope.renewBikeMainForm.regId);
		//$location.path("/RenewBikeMain1/"+$scope.renewBikeMainForm.regId);
		window.location="/Fastlane_Vehicle_Insurance/partials/RenewBikeMain1.html";
	}
})

ap.controller("RenewBikeMain1Controller",function($scope, $http,$routeParams, $location, $cookies){
	
	//console.log("inside RenewBikeMain1Controller");
	
	$scope.logOut = function(){
		//console.log("logout")
		$cookies.put("userName",undefined);
		//console.log($cookies.get("userName"))
		window.location = "index.html";
	}
	/*alert("inside main1 controller")*/
	$scope.renewBikeMain1Form = {};
	$scope.renewBikeMain1Form.regId = $cookies.get('regId');
	$scope.premiumList=[];
	$scope.renewBikeMain1Form.idv=null;
	$scope.renewBikeMain1Form.dateOfPurchase=null;
	$scope.renewBikeMain1Form.finalPremium=null;
	//$scope.buyPolicy.message=null;
	$scope.renewBikeMain1Form.policyNo=null;
	$scope.premium=null;
	$scope.renewBikeMain1Form.policyType=null;
	$scope.renewBikeMain1Form.policy_date=null;
	$scope.renewBikeMain1Form.expiry_date=null;
	$scope.renewBikeMain1Form.temp=null;
	
	$scope.renewBikeMain1Form.getPremium=function(){
		
		/*alert('in premium function')*/
		//console.log("in premium function");
		$scope.renewBikeMain1Form.regId = parseInt($scope.renewBikeMain1Form.regId);
		var data = angular.toJson($scope.renewBikeMain1Form);
		
		/*$scope.buyPolicy.regId=$cookies.get('regId');*/
		
		//console.log("regId-: "+ $scope.renewBikeMain1Form.regId);
		/*alert($scope.renewBikeMain1Form.regId)
		alert(data);*/
		//console.log(data);
		$http.post(URI+'Insurance/getBikeDetails',data).then(function(res){
			/*alert("hi")*/
			$scope.renewBikeMain1Form=res.data[0];
			$scope.renewBikeMain1Form.finalPremium=null;
			/*alert(res.data);
*/			//alert($scope.buyPolicy.idv);*/
			//console.log(res.data);
			//console.log($scope.renewBikeMain1Form.idv);
			var now  = new Date();
			var years =  Math.abs(now.getUTCFullYear() - 5);
			now.setFullYear(years);
			//console.log(years);
			now.setMonth(now.getMonth());
			now.setDate(now.getDate());
			//console.log(now.toLocaleString);
			//console.log($scope.renewBikeMain1Form.dateOfPurchase.toLocaleString);
			if($scope.renewBikeMain1Form.dateOfPurchase.toLocaleString>=now.toLocaleString)
				{
				//alert("inside if");
				//console.log("inside if");
				$scope.premium=Math.round((3127*$scope.renewBikeMain1Form.idv)/100000);
				}
			else
				{
				//alert("inside else");
				//console.log("inside else");
				//alert($scope.buyPolicy.idv);
				//console.log("$scope.renewBikeMain1Form.idv");
				$scope.premium=Math.round((3283*$scope.renewBikeMain1Form.idv)/100000);
				}
			
			$scope.premiumList[0]=$scope.premium;
			//console.log($scope.premiumList[0]);
			$scope.premiumList[1]=$scope.premium+2000;
			//console.log($scope.premiumList[1]);
			
		
			
		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyPolicy.message=res.data.message;
			$scope.renewBikeMain1Form=null;
		}
		
        $scope.addPremium=function(){
        	/*alert("in add premium");
        	alert("final premium is: ");
        	alert($scope.buyPolicy.finalPremium);*/
        	
        	//console.log("temp"+$scope.renewBikeMain1Form.temp)
        	//console.log("in add premium");
        	//console.log("final premium is: ");
        	//console.log($scope.renewBikeMain1Form.finalPremium);
        	
        	if($scope.renewBikeMain1Form.finalPremium!=null){
        	$scope.renewBikeMain1Form.temp=1;
        	$scope.renewBikeMain1Form.finalPremium = parseFloat($scope.renewBikeMain1Form.finalPremium);
        	$scope.renewBikeMain1Form.policy_date= new Date();
        	$scope.renewBikeMain1Form.policy_date = $scope.renewBikeMain1Form.policy_date.toLocaleString();
        	$scope.renewBikeMain1Form.expiry_date = new Date();
        	var months = Math.abs($scope.renewBikeMain1Form.expiry_date.getMonth()+6);
        	$scope.renewBikeMain1Form.expiry_date.setMonth(months);
        	$scope.renewBikeMain1Form.expiry_date = $scope.renewBikeMain1Form.expiry_date.toLocaleString();
        	if($scope.premiumList[0] == $scope.renewBikeMain1Form.finalPremium)
    			$scope.renewBikeMain1Form.policyType = "Basic";
    		else
    			$scope.renewBikeMain1Form.policyType = "Prime";
        	var data = angular.toJson($scope.renewBikeMain1Form);
    		/*alert(data);*/
        	//console.log(data)
    		$http.post(URI+'Insurance/addBikePremium',data).then(function(res){
    			//$scope.buyPolicy.message=res.data.message;
    			//console.log(res.data)
    			$scope.renewBikeMain1Form = res.data;/*
    			$scope.buyPolicy.policyNo=res.data;*/
    			$scope.renewBikeMain1Form.temp = 1;
    			/*alert($scope.renewBikeMain1Form.policyNo);
*/    		
    		}),function(res){
    			//alert("In Controller: get function failure ");
    			//console.log("In Controller: get function failure ");
    			$scope.renewBikeMain1Form.message=res.data.message;
    			$scope.renewBikeMain1Form=null;
    		}
        	}
        	else{
        		$scope.renewBikeMain1Form.temp=2;
        		//console.log("inside else")
        	}
    		
        }
	
	}
});