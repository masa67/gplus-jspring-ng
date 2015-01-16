'use strict';

angular
    .module('UserService', ['UserService'])
    .factory('user', ['$resource', function($resource) {

        return $resource('', {}, {
        	getUser: {method: 'GET', url: '/user', isArray: false},
        	updateUser: {method: 'POST', url: '/user', isArray: false},
        	doAuth: {method: 'PUT', url: '/user/auth', isArray: false},
        	deleteAuth: {method: 'DELETE', url: '/user/auth'}
            });
}]);