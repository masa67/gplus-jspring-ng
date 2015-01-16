'use strict;'

angular.module('MainCtrl', []).controller('MainController',
    ['$http', '$timeout', '$scope', 'user', function($http, $timeout, $scope, user) {

    $scope.config = {};
    $scope.authResult = undefined;
    $scope.googleSignIn = undefined;
    $scope.user = undefined;
	
	$scope.models = {
		nickname: ''
	}
	
    user.getUser(function(result) {
    	
    	$scope.config = result;
    	$scope.user = result.user;
    	$scope.googleSignIn = result.googleSignIn;
    	
    	$scope.clientId = $scope.config.googleSignIn.clientId;
    	
    	if ($scope.user == undefined || $scope.user.googlePerson === undefined)
    		$timeout(function() {
    			gapi.signin.go();
    		}, 0);
    }, function(err) {
    	console.log(err);
    });
    
    $scope.clickRender = function() {
    	gapi.signin.go();
    }
    
    $scope.clickSignOut = function() {
    	user.deleteAuth(function(result) {
    		$scope.user = undefined;
			$timeout(function() {
				gapi.signin.go();
			}, 0);
    	}, function(err) {
    		console.log(err);
    	});
    };
    
    $scope.clickUpdateNickname = function() {
    	user.updateUser({ nickname: $scope.models.nickname }, function(result) {
            $scope.user = result.user;
        }, function(err) {
            console.log(err);
        });
    };
    
    $scope.connectServer = function() {
    	user.doAuth({
    		state: $scope.googleSignIn.state,
    		code: $scope.authResult.code
    	}, function(result) {
    		$scope.user = result.user;
    	}, function(err) {
    		console.log(err);
    	});
    };
    
    $scope.onSignInCallback = function(authResult) {
		if (authResult['access_token']) {
			if (authResult['status']['method'] === 'PROMPT') {
				$scope.authResult = authResult;
				$scope.connectServer();
			}
		} else {
			if (authResult['error'] !== 'immediate_failed' &&
				authResult['error'] !== 'user_signed_out') {
				console.log('sign-in failed: ' + authResult['error']);
			}
		}
	};
}]);
