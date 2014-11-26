//ngRoute if it doesnt use, promise and resolve object in response service.
var mieventoApp = angular.module("mieventoApp", [ "ngCookies", "ngRoute", "ui.bootstrap", "ngAnimate", "ui.router", "ui.select", "ui.calendar",
                                                  "mieventoControllers", "mieventoServices", "mieventoContext"]);

var mieventoControllers = angular.module("mieventoControllers", []);
var mieventoContext = angular.module("mieventoContext",[]);
var mieventoServices = angular.module("mieventoServices",["ngResource"]);


mieventoApp.run([ "$rootScope", "$cookies", "$state", "userService", "applicationContext",
		function($rootScope, $cookies, $state, userService, applicationContext) {

	if ($cookies.token){
		$rootScope.token = $cookies.token;
	}
	//FIXME horroorrr !!
	if ($rootScope.loggedUser == null && $cookies.logged_email){
		var user = {"email" : $cookies.logged_email,"password" : $cookies.logged_pass}
	
		userService.login(user, function(data) {
			$rootScope.login(data);
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
	}

	$rootScope.login = function(user) {

		$rootScope.token = user.idSession;
		$rootScope.loggedUser = user;
		$cookies.token = $rootScope.token;
		$cookies.logged_email = $rootScope.loggedUser.email;
		$cookies.logged_pass = $rootScope.loggedUser.password;
		applicationContext.getUserContext().setLoggedUser(user);
		
		$state.go("eventState.events");
	};

	$rootScope.logout = function() {
		$rootScope.token = null;
		$rootScope.loggedUser = null;

		delete $cookies.token;
		delete $cookies.logged_email;
		$state.go("homeState");
	}

	$rootScope.selectEvent = function(event){
		applicationContext.getEventContext().setSelectedEvent(event);
		$state.go("eventState.events");
	}

	
	 $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
		 applicationContext.setPreviousState(from);
//		 console.log(to.name);
//		 console.log(to.name.search("eventState"));
//		 //if go to any event page you first selectedEvent
//		 if (to.name.search("eventState") != -1){
//			 var loggedUser = applicationContext.getUserContext().getLoggedUser();
//			 console.log(loggedUser);
//			 if (loggedUser == null){
//				 $state.go('loginState');
//			 }
//			 $state.go('eventState.events');
//		 }
     });
		 
} ]);





