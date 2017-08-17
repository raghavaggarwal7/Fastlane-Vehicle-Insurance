ap.controller("CustomerProfileController", function($scope, $http,$location, $routeParams,$cookies) {
	//alert("in profile controller")
	//console.log("inside customer profile controller");

	//console.log("i am user "+ $cookies.get('userName'));

	$scope.viewForm={};
	$scope.viewFormList=null;
	$scope.viewForm.userName=$cookies.get('userName');
	$scope.viewForm.claimStatus = null;
	$scope.viewForm.policyNo = null;
	$scope.viewForm.message = null;

	$scope.getProfile=function(){
		$scope.viewFormList = null;
		//alert('in getProfile function')
		//console.log("in getCarProfile function");
		//$scope.viewForm.userName = parseInt($scope.buyPolicy.regId);
		var data = angular.toJson($scope.viewForm);
		//console.log($scope.viewForm.userName);
		/*$scope.buyPolicy.regId=$cookies.get('regId');*/

		//console.log("userName: "+ $scope.viewForm.userName);
		//alert(data);
		//console.log(data);
		$http.post(URI+'Insurance/getProfileDetails',data).then(function(res){
			/*for (var int = 0; int < res.data.length; int++) {
				var array_element = res.data[int];
				console.log(array_element)

			}*/
			//console.log("in success: getProfileDetails")
			$scope.viewFormList=res.data;/*
			console.log("car brand-"+$scope.viewForm.carBrand);*/
			//alert(res.data);
			//alert($scope.buyPolicy.idv);*/
			//console.log($scope.viewFormList);
			//console.log($scope.buyPolicy.idv);
			/*$cookies.put('userName', $scope.viewForm.userName, {expires : exp});*/
			//$location.path("/myAccount/"+$scope.viewForm.userName);
			//console.log($scope.viewForm.userName)

		}),function(res){
			//alert("In Controller: get function failure ");
			//console.log("In Controller: get function failure ");
			//$scope.buyPolicy.message=res.data.message;
			//console.log(res)
			//$scope.viewFormList=null;
		}

	}
	
	$scope.claim = function(claimStatus, policyNo){
		$scope.viewForm.policyNo = parseInt(policyNo);
		
		//console.log(policyNo)
		//console.log(claimStatus)
		$scope.viewForm.claimStatus = claimStatus;
		if(claimStatus == "CLAIM NOW"){
			
			var data = angular.toJson($scope.viewForm);
			$http.post(URI + "Insurance/updateclaimstatus", data).then(function(response) {
				//console.log("in success - claim");
				alert(response.data.message);
				$scope.viewForm.message = response.data.message;
				//console.log($scope.viewForm.message);
				$scope.getProfile();
			}, function(response) {
				//console.log("in failure - claim");
			})
		}
	}
})