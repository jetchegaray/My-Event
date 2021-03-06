TAG_SELECTED_EVENT_UPDATE = "selectedEventUpdated"
TAG_SUMMARY_VIEW_BUDGET_UPDATE = "summaryViewBudgetUpdated"
TAG_PLACE_DELETE_UPDATE= "placeDeleteUpdate"

mieventoControllers.controller("EventListController", ["$rootScope", "$scope", "$state", "applicationContext",
		function($rootScope, $scope, $state, applicationContext) {

			$scope.events = applicationContext.getUserContext().getLoggedUserEvents();
			$scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
			$scope.loggedUser = applicationContext.getUserContext().getLoggedUser();
				
			$scope.goAddEvent = function() {
				$state.go("eventState.eventCreate");
			};
		
			$scope.goToEditEvent = function(event) {
				applicationContext.getEventContext().setEditEvent(event);
				$state.go("eventState.eventEdit");
			};
			
			$scope.setSelectEvent = function(event){
				if (angular.equals($scope.selectedEvent,event)){
					$scope.selectedEvent = null;
				}else{
					$scope.selectedEvent = event;
				}
			
				applicationContext.getEventContext().setSelectedEvent($scope.selectedEvent);
				$rootScope.$broadcast(TAG_SELECTED_EVENT_UPDATE);
				$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
			};

} ]);


mieventoControllers.controller("DetailEventController", [ "$rootScope", "$scope", "$state", "userService", "eventService", "applicationContext",
		function($rootScope, $scope, $state, userService, eventService, applicationContext) {

			eventService.getAllEventTypes(function(data) {
				$scope.eventTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		
			$scope.save = function() {
				
				if ($scope.eventForm.$invalid){
					return false;
				}
				
				applicationContext.getUserContext().addUserEvent($scope.event);
					
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					applicationContext.getEventContext().setSelectedEvent($scope.event);
					$state.go("eventState.events");
					$rootScope.$broadcast(TAG_SELECTED_EVENT_UPDATE);
					$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
							
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			};

} ]);



mieventoControllers.controller("EditEventController", [ "$scope", "$state", "userService", "eventService", "applicationContext",
	function($scope, $state, userService, eventService, applicationContext) {
		 
		$scope.event = applicationContext.getEventContext().getEditEvent(event);
	
		eventService.getAllEventTypes(function(data) {
			$scope.eventTypes = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		$scope.save = function() {
			
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				applicationContext.getEventContext().setSelectedEvent(event);
				$state.go("eventState.events");
   					
   				}, function(error) {
   					applicationContext.getExceptionContext().setDanger(error.data);
   				});
   		};

} ]);



mieventoControllers.controller("SelectedEventController", [ "$scope", "applicationContext", function($scope, applicationContext) {
	
	 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
	 //if doesnt selected, observer the event to change.
	 $scope.$on(TAG_SELECTED_EVENT_UPDATE, function() {
		 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
     });	
} ]);


mieventoControllers.controller("SummaryViewEventController", [ "$scope", "userService","applicationContext", function($scope,userService, applicationContext) {
	
	$scope.summaryViewEvent = applicationContext.getEventContext().getSelectedEvent();
	$scope.summaryViewBudget = applicationContext.getEventContext().getTotalBudgetSelectedEvent();

	//if doesnt selected, observer the event to change.
	$scope.$on(TAG_SELECTED_EVENT_UPDATE, function() {
		$scope.summaryViewEvent = applicationContext.getEventContext().getSelectedEvent();
	});
 
	$scope.$on(TAG_SUMMARY_VIEW_BUDGET_UPDATE, function() {
		$scope.summaryViewBudget = applicationContext.getEventContext().getTotalBudgetSelectedEvent();
	});
	
	//Cuando se borra un proveedor de lugar, el place al que apunta tambien debe borrarse.
	$scope.$on(TAG_PLACE_DELETE_UPDATE, function() {
		 applicationContext.getEventContext().deletePlace();
		 var user = applicationContext.getUserContext().getLoggedUser();
		 userService.update(user, function() {
			applicationContext.getUserContext().setLoggedUser(user);
		 }, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		 });
    });
} ]);

