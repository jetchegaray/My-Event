

mieventoServices.factory("userService",["$resource",function($resource){
		return $resource("/mievento/user/:requestMapping",{},
				{
					signUp : {method: "POST", headers: {'Content-Type': 'application/json'}},
					login : {method : "PUT", headers: {'Content-Type': 'application/json'}},
					logout: {method : "DELETE"},
					forgottenPassword: {method : "PUT",params: {requestMapping : "email"}},
					update : {method : "POST", params : {requestMapping : "update"}, headers: {'Content-Type': 'application/json'}}
				})
		}
]);


mieventoServices.factory("providerService",["$resource",function($resource){
	    return $resource("/mievento/provider/:requestMapping/:pathParams",{
        	requestMapping: "@requestMapping",
        	pathParams: "@pathParams"
        },
        {
        	getAll : {method : "GET",params: {requestMapping: "all"},isArray:true},
        	getAllTypes : {method : "GET",params: {requestMapping: "types"},isArray:true},
        	getPlaceTypes : {method : "GET",params: {requestMapping: "placeTypes"},isArray:true},
        	getByType : {method : "GET",params: {requestMapping: "byType"},isArray:true}
        });
       }                                     
]);


mieventoServices.factory("eventGuestService",["$resource", function($resource){
		return $resource("/mievento/guest/:requestMapping",{
			requestMapping : "@requestMapping"},
			{
				getAllStatusTypes : {method : "GET",params : { requestMapping : "statusTypes"}, isArray : true},
				sendInvitation : {method : "PUT", params : {requestMapping : "sendInvitation"}}
			});	
}])
