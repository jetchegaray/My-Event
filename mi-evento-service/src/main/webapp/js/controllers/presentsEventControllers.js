

mieventoControllers.controller("PresentsEventController", ["$scope", "$state", "applicationContext", "userService",
                function($scope, $state, applicationContext, userService) {
		
		$scope.presents = applicationContext.getEventContext().getPresents();
		if ($scope.presents.length == 0){
			$state.go("eventState.presentsCreate");
		}
		
		$scope.goAddPresent = function(){
			$state.go("eventState.presentsCreate");
		}
		
		$scope.save = function(data) {
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
		$scope.addDescription = function(description,present) {
			if (present.descriptions == null){
				present.descriptions = [];
			}
			present.descriptions.push(description);
			
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
 } ]);



mieventoControllers.controller("DetailPresentEventController", ["$scope", "$state", "applicationContext", "userService", "countryService", 
                function($scope, $state, applicationContext, userService, countryService) {
	
	
		$scope.save = function() {
			var location = {
					countryCode :  $scope.present.search.country.name,
					province :  $scope.present.search.state.name,
					city :  $scope.present.search.city.name,
					streetAddress : $scope.present.locationCredit.streetAddress
			};
			
			$scope.present.locationCredit = location;
			console.log(angular.toJson($scope.present));
			applicationContext.getEventContext().addPresent($scope.present);
		
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$state.go("eventState.presents");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
 } ]);
