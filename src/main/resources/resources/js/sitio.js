/* 
 * Copyright 2015 Codigo Fantasma.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * https://github.com/bijukunjummen/spring-boot-mvc-test/tree/withangular
 */
var app = angular.module("sitioApp", ["ui.router", "ngResource"]);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: URLS.partialsList,
                controller: 'SitioCtrl'
            })
            .state('editar', {
                url: '/editar/:sitioId',
                templateUrl: URLS.partialsEdit,
                controller: 'SitioEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: URLS.partialsCreate,
                controller: 'SitioCtrl'
            });
});


app.factory("Sitio", function($resource) {
    return $resource(URLS.hotels, {id: "@id"}, {
        update: {
            method: 'PUT'
        }
    });
});

app.controller("SitioCtrl", function($scope, Sitio, $state) {
    function init() {
        $scope.getSitios();
    }


    $scope.getSitios = function() {
        $scope.sitios = Sitio.query();
    };

    $scope.deleteSitio = function(sitio) {
        return sitio.$delete({}, function() {
            $scope.sitios.splice($scope.sitios.indexOf(sitio), 1);
        });
    };

    $scope.createSitio = function() {
        var sitio = new Sitio($scope.sitio);
        sitio.$save({}, function() {
            $state.transitionTo("lista");
        });
    };

    init();
});

app.controller("SitioEditarCtrl", function($scope, Sitio, $state, $stateParams) {
    function init() {
        $scope.sitio = Sitio.get({id: $stateParams.sitioId});
    }

    $scope.updateSitio = function() {
        var sitio = new Sitio($scope.sitio);
        sitio.$update().then(function() {
            $state.transitionTo("lista");
        });
    };
    init();
});

