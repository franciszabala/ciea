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
var ctrl = angular.module("perfil.controller", []);

ctrl.controller("PerfilCtrl", function ($scope, Perfil, TiposTelefono, TiposCorreo, Encriptar) {
    $scope.init = function () {
        $scope.tiposTelefono = TiposTelefono;
        $scope.tiposCorreo = TiposCorreo;
        $scope.cambio = false;
        $scope.alertaGuardado = false;
        $scope.usuario = Perfil.get({}, function () {
        }, function (error) {
            $scope.alertaError(error);
        });
    };
    
    $scope.cambioClave = function () {
        $scope.cambio = true;
        $scope.usuario.clave = "";
    };

    $scope.updateUsuario = function () {
        if ($scope.usuario.clave && $scope.cambio) {
            var hash = Encriptar($scope.usuario.clave);
            $scope.usuario.clave = hash.toString();
        }
        var usuario = new Perfil($scope.usuario);
        usuario.$update({}, function () {
            $scope.alertaGuardado = true;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.createTelefono = function () {
        $scope.usuario.telefonos.push({id: 0});
    };

    $scope.deleteTelefono = function (telefono) {
        $scope.usuario.telefonos.splice($scope.usuario.telefonos.indexOf(telefono), 1);
    };

    $scope.createCorreo = function () {
        $scope.usuario.correos.push({id: 0});
    };

    $scope.deleteCorreo = function (correo) {
        $scope.usuario.correos.splice($scope.usuario.correos.indexOf(correo), 1);
    };

    $scope.init();
});
