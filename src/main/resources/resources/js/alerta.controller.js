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

var actrl = angular.module("alerta.controller", []);

actrl.controller("AlertaCtrl", function ($scope, $log) {

    $scope.alertas = [];

    $scope.agregarAlerta = function (tipo, mensaje) {
        $scope.alertas.push({type: tipo, msg: mensaje});
    };

    $scope.alertaExito = function (mensaje) {
        $log.log(mensaje);
        $scope.agregarAlerta('success', mensaje);
    };

    $scope.alertaInformacion = function (mensaje) {
        $log.info(mensaje);
        $scope.agregarAlerta('info', mensaje);
    };

    $scope.alertaAdvertencia = function (mensaje) {
        $log.warn(mensaje);
        $scope.agregarAlerta('warning', mensaje);
    };

    $scope.alertaPeligro = function (mensaje) {
        $log.error(mensaje);
        $scope.agregarAlerta('danger', mensaje);
    };

    $scope.alertaError = function (error) {
        $scope.limpiarAlertas();
        if (error.status === 422) {//error de validacion
            error.data.errores.forEach(function (item) {
                $log.debug(item.mensaje);
                $scope.agregarAlerta('danger', item.mensaje);
            });
        } else {//error de sistema
            $log.error(error.data.mensaje);
            $scope.agregarAlerta('danger', error.data.mensaje);
        }
    };

    $scope.limpiarAlertas = function () {
        while ($scope.alertas.length > 0) {
            $scope.alertas.pop();
        }
    };

    $scope.cerrarAlerta = function (index) {
        $scope.alertas.splice(index, 1);
    };

});
