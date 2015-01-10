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
var app = angular.module("sitioApp", ["ui.router", "ngResource", "ui.bootstrap"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: "/sitios/lista",
                controller: 'SitioListaCtrl'
            })
            .state('editar', {
                url: '/editar/:sitioId',
                templateUrl: "/sitios/editar",
                controller: 'SitioEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: "/sitios/nuevo",
                controller: 'SitioNuevoCtrl'
            });
});


app.factory("Sitio", function ($resource) {
    return $resource("/api/sitio/:id", {id: "@id"}, {
        update: {
            method: 'PUT'
        },
        page: {
            method: 'GET'
        }
    });
});

app.controller("SitioListaCtrl", function ($scope, Sitio, $state) {
    function init() {
        $scope.pagina = 1;
        $scope.getSitios();
    }
    ;

    $scope.getSitios = function () {
        Sitio.page({'page.page': $scope.pagina}, function (data) {
            $scope.sitios = data;
//            $scope.total = data.totalElements;
//            $scope.sitios = data.content;
        });
//        $scope.sitios = Sitio.query();

    };

    $scope.deleteSitio = function (sitio) {
        return Sitio.delete({id:sitio.id}, function () {
            $scope.sitios.content.splice($scope.sitios.content.indexOf(sitio), 1);
//            $scope.getSitios();
        });
    };

    $scope.pageChanged = function () {
        $scope.getSitios();
    };

    init();
});

app.controller("SitioNuevoCtrl", function ($scope, Sitio, $state) {
    $scope.createSitio = function () {
        var sitio = new Sitio($scope.sitio);
        sitio.$save({}, function () {
            $state.transitionTo("lista");
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

app.controller("SitioEditarCtrl", function ($scope, Sitio, $state, $stateParams) {
    function init() {
        $scope.id = $stateParams.sitioId;
        $scope.sitio = Sitio.get({id: $stateParams.sitioId});
    }
    ;

    $scope.updateSitio = function () {
        var sitio = new Sitio($scope.sitio);
        sitio.$update().then(function () {
            $state.transitionTo("lista");
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };

    init();
});

