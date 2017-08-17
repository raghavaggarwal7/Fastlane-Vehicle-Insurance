var config={
		"protocol" : "http",
		"port" : "9507",
		"domain" : "localhost",
		"project" : "Fastlane_Vehicle_Insurance"
}

/*
 * Do not change the code below this point
 * Only change the port number in the config object
 */

function getURI(){
	//alert("Inside getURI");
	//console.log("Inside getURI of properties");
	return config.protocol +"://"+ config.domain +":"+ config.port +"/"+ config.project +"/api/" ;
}