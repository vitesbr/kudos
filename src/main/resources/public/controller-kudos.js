var app = angular.module('myApp', ['ngMaterial']);

function setFocus(id) {
  setTimeout(function() {
    $(id).focus();
  }, 250);
}

function isNullOrEmpty(val) {
  return val == undefined || val == null || val.length == 0;
}

app.controller("KudosController", function($http, $scope, $rootScope, $window, $q, $mdDialog) {

  var controller = this;

  controller.onReady = function() {
	  controller.home();
  };

  controller.initialize = function() {
	  controller.users = [];
	  controller.usersToGive = [];
	  controller.user = {};
	  controller.kudos = [];
	  controller.showUserLogin = false;
	  controller.showUserData = false;
	  controller.idUserLogin = null;
	  controller.idUserToGive = null;
	  controller.pointsToGive = null;
  }

  controller.home = function() {
	  controller.initialize();
	  controller.getUsers();
  }

  controller.getUsers = function() {
	  $http.get("http://localhost:8080/api/users")
	  .success(function(data) {
		  controller.users = data;
		  controller.showUserLogin = true;
		  controller.showUserData = false;
	  }).error(function(err) {
		console.error(angular.toJson(err));
	  });
  }

  controller.userLogin = function(idUser) {
	controller.idUserLogin = idUser;
	controller.showUserLogin = false;
	controller.showUserData = false;
	controller.getUser();
  }

  controller.getUser = function() {
	  controller.user = {};
	  $http.get("http://localhost:8080/api/users/" + controller.idUserLogin)
	  .success(function(data) {
		  controller.user = data;
		  controller.showUserData = true;
		  controller.getKudos();
		  controller.getUsersToGive();
	  }).error(function(err) {
		console.error(angular.toJson(err));
	  });
  }

  controller.getKudos = function() {
	  controller.kudos = [];
	  $http.get("http://localhost:8080/api/kudos/" + controller.idUserLogin)
	  .success(function(data) {
		  controller.kudos = data;
	  }).error(function(err) {
		console.error(angular.toJson(err));
	  });
  }

  controller.getUsersToGive = function() {
	  controller.usersToGive = [];
	  controller.idUserToGive = null;
	  controller.pointsToGive = null;
	  for (var x = 0; x < controller.users.length; x++) {
		  var user = controller.users[x];
		  if (user.id != controller.idUserLogin) {
			  controller.usersToGive.push(user);
		  }
	  }
  }

  controller.giveKudos = function() {

	  if (isNullOrEmpty(controller.idUserToGive)) {
		  setFocus("#comboUser");
		  return;

	  } else if (isNullOrEmpty(controller.pointsToGive)) {
		  setFocus("#inputPoints");
		  return;
	  }

	  var give = {};

	  give.idUserGiven = controller.idUserLogin;
	  give.idUserReceived = controller.idUserToGive;
	  give.points = controller.pointsToGive;

	  console.log(give);

	  $http.post("http://localhost:8080/api/kudos/", give)
	  .success(function(data) {
		  controller.userLogin(controller.idUserToGive);
	  }).error(function(err, stts) {
		  console.error(angular.toJson(err));
	  });
  }

});
