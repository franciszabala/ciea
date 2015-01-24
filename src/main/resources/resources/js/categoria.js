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
 */

var app = angular.module("categoriaApp", ["ui.router", "ngResource", "ui.bootstrap"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: "/categorias/lista",
                controller: 'CategoriaListaCtrl'
            })
            .state('editar', {
                url: '/editar/:categoriaId',
                templateUrl: "/categorias/editar",
                controller: 'CategoriaEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: "/categorias/nuevo",
                controller: 'CategoriaNuevoCtrl'
            });
});


app.factory("Categoria", function ($resource) {
    return $resource("/api/categoria/:id", {id: "@id"}, {
        update: {
            method: 'PUT'
        },
        page: {
            method: 'GET',
            params: {id: 'page'}
        }
    });
});

app.controller("CategoriaListaCtrl", function ($scope, Categoria) {
    $scope.init = function () {
        $scope.pagina = 1;
        $scope.getCategorias();
    };

    $scope.getCategorias = function () {
        Categoria.page({'page': $scope.pagina}, function (data) {
            $scope.categorias = data;
        }, function (error) {
            //error
        });
    };

    $scope.deleteCategoria = function (categoria) {
        return Categoria.delete({id: categoria.id}, function () {
            $scope.getCategorias();
        });
    };

    $scope.pageChanged = function () {
        $scope.getCategorias();
    };

    $scope.init();
});

app.controller("CategoriaNuevoCtrl", function ($scope, Categoria, $state) {
    $scope.createCategoria = function () {
        var categoria = new Categoria($scope.categoria);
        categoria.$save({}, function () {
            $state.transitionTo("lista");
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

app.controller("CategoriaEditarCtrl", function ($scope, Categoria, $state, $stateParams) {
    $scope.init = function () {
        $scope.categoria = Categoria.get({id: $stateParams.categoriaId});
    };

    $scope.updateCategoria = function () {
        var categoria = new Categoria($scope.categoria);
        categoria.$update().then(function () {
            $state.transitionTo("lista");
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    }

    $scope.init();
});