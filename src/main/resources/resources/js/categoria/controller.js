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
var ctrl = angular.module("categoria.controller", []);

ctrl.controller("CategoriaListaCtrl", function($scope, Categoria) {
    $scope.init = function() {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getCategorias();
    };

    $scope.showModal = function (id) {
        $scope.borrarId = id;
        $scope.alertaBorrado = false;
        $scope.modalBorrado = true;
    };

    $scope.hideModal = function () {
        $scope.modalBorrado = false;
    };

    $scope.getCategorias = function() {
        Categoria.page({page: $scope.pagina}, function(data) {
            $scope.categorias = data;
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.getCategoriasList = function() {
        Categoria.query({}, function(data) {
            $scope.categorias = data;
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteCategoria = function() {
        $scope.modalBorrado = false;
        return Categoria.delete({id: $scope.borrarId}, function() {
            $scope.alertaBorrado = true;
            $scope.getCategorias();
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function() {
        $scope.getCategorias();
    };

    $scope.init();
});

ctrl.controller("CategoriaNuevoCtrl", function($scope, Categoria, $state) {
    $scope.createCategoria = function() {
        var categoria = new Categoria($scope.categoria);
        categoria.$save({}, function() {
            $state.transitionTo("lista");
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function() {
        $state.transitionTo("lista");
    };
});

ctrl.controller("CategoriaEditarCtrl", function($scope, Categoria, $state, $stateParams) {
    $scope.init = function() {
        $scope.categoria = Categoria.get({id: $stateParams.categoriaId}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.updateCategoria = function() {
        var categoria = new Categoria($scope.categoria);
        categoria.$update({}, function() {
            $state.transitionTo("lista");
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function() {
        $state.transitionTo("lista");
    };

    $scope.init();
});