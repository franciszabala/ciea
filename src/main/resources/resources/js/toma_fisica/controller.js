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
var ctrl = angular.module("toma_fisica.controller", []);

ctrl.controller("TomaFisicaListaCtrl", function ($scope, TomaFisica) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getTomaFisicas();
    };

    $scope.showModal = function (id) {
        $scope.borrarId = id;
        $scope.alertaBorrado = false;
        $scope.modalBorrado = true;
    };

    $scope.hideModal = function () {
        $scope.modalBorrado = false;
    };

    $scope.getTomaFisicas = function () {
        TomaFisica.page({page: $scope.pagina}, function (data) {
            $scope.toma_fisicas = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteTomaFisica = function () {
        $scope.modalBorrado = false;
        return TomaFisica.delete({id: $scope.borrarId}, function () {
            $scope.alertaBorrado = true;
            $scope.getTomaFisicas();
        }, function (error) {
            $scope.alertaError(error);
        });
    };


    $scope.iniciarTomaFisica = function () {
        var toma_fisica = new TomaFisica($scope.toma_fisica);
        toma_fisica.iniciar({}, function () {
        }, function (error) {
            $scope.alertaError(error);
        });
    };


    $scope.terminarTomaFisica = function () {
        $scope.modalBorrado = false;
        return TomaFisica.update({id: $scope.borrarId}, function () {
            $scope.alertaBorrado = true;
            $scope.getTomaFisicas();
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getTomaFisicas();
    };

    $scope.init();
});

ctrl.controller("TomaFisicaNuevoCtrl", function ($scope, TomaFisica, EstadoTomaFisica, $state) {
    $scope.init = function () {
        $scope.estados = EstadoTomaFisica;
    };

    $scope.createTomaFisica = function () {
        var toma_fisica = new TomaFisica($scope.toma_fisica);
        toma_fisica.$save({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };


    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.init();
});

ctrl.controller("TomaFisicaEditarCtrl", function ($scope, TomaFisica, EstadoTomaFisica, Activo, Sitio, ActivoEstado, TomaFisicaDetalle, $state, $stateParams) {
    $scope.init = function () {
        $scope.toma_fisica = TomaFisica.get({id: $stateParams.tomaFisicaId});
        $scope.estados = EstadoTomaFisica;
        $scope.activo_estados = ActivoEstado;
        $scope.activos = Activo.listar_activo();
        $scope.sitios = Sitio.query();
    };

    $scope.guardarTomaFisica = function (activo) {
        var toma_fisica_detalle = new TomaFisicaDetalle();
        toma_fisica_detalle.activo = activo;
        toma_fisica_detalle.tomaFisica = $scope.toma_fisica;
        toma_fisica_detalle.$update({}, function () {
            $scope.activos = Activo.listar_activo();
        }, function (error) {
            $scope.alertaError(error);
        });
    };


    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };

    $scope.init();
});


