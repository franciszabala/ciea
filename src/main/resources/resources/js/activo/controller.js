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
var ctrl = angular.module("activo.controller", []);

ctrl.controller("ActivoListaCtrl", function ($scope, Activo) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getActivos();
    };

    $scope.getActivos = function () {
        Activo.page({page: $scope.pagina}, function (data) {
            $scope.activos = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteActivo = function (activo) {
        return Activo.delete({id: activo.id}, function () {
            $scope.getActivos();
            $scope.alertaExito('Se borro correctamente');
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getActivos();
    };

    $scope.init();
});

ctrl.controller("ActivoNuevoCtrl", function ($scope, Activo, Sitio, Articulo, TipoEstados, $state) {
    $scope.sitios = Sitio.query();
    $scope.articulos = Articulo.query();
    $scope.estados = TipoEstados;
    $scope.createActivo = function () {
        var activo = new Activo($scope.activo);
        activo.$save({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

ctrl.controller("ActivoEditarCtrl", function ($scope, Activo, Sitio, Articulo, TipoEstados, $state, $stateParams) {
    $scope.init = function () {
        $scope.activo = Activo.get({id: $stateParams.activoId});
        $scope.sitios = Sitio.query();
        $scope.estados = TipoEstados;
        $scope.articulos = Articulo.query();
    };

    $scope.updateActivo = function () {
        var activo = new Activo($scope.activo);
        activo.$update().then(function () {
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