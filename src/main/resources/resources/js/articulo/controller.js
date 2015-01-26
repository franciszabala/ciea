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
var ctrl = angular.module("articulo.controller", []);

ctrl.controller("ArticuloListaCtrl", function ($scope, Articulo) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getArticulos();
    };

    $scope.getArticulos = function () {
        Articulo.page({'page': $scope.pagina}, function (data) {
            $scope.articulos = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteArticulo = function (articulo) {
        return Articulo.delete({id: articulo.id}, function () {
            $scope.getArticulos();
            $scope.alertaExito('Se borro correctamente');
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getArticulos();
    };

    $scope.init();
});

ctrl.controller("ArticuloNuevoCtrl", function ($scope, Articulo, Categoria, $state) {
    $scope.categorias = Categoria.query();    
    $scope.createArticulo = function () {
        var articulo = new Articulo($scope.articulo);
        articulo.$save({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

ctrl.controller("ArticuloEditarCtrl", function ($scope, Articulo, Categoria, $state, $stateParams) {
    $scope.init = function () {
        $scope.articulo = Articulo.get({id: $stateParams.articuloId});
        $scope.categorias = Categoria.query();
    };

    $scope.updateArticulo = function () {
        var articulo = new Articulo($scope.articulo);
        articulo.$update().then(function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    }

    $scope.init();
});