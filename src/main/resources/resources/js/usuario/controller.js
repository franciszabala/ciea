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
var ctrl = angular.module("usuario.controller", []);

ctrl.controller("UsuarioListaCtrl", function($scope, Usuario) {
    $scope.init = function() {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getUsuarios();
    };

    $scope.showModal = function (id) {
        $scope.borrarId = id;
        $scope.alertaBorrado = false;
        $scope.modalBorrado = true;
    };

    $scope.hideModal = function () {
        $scope.modalBorrado = false;
    };

    $scope.getUsuarios = function() {
        Usuario.page({page: $scope.pagina}, function(data) {
            $scope.usuarios = data;
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteUsuario = function() {
        $scope.modalBorrado = false;
        return Usuario.delete({id: $scope.borrarId}, function() {
            $scope.alertaBorrado = true;
            $scope.getUsuarios();
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function() {
        $scope.getUsuarios();
    };

    $scope.init();
});

ctrl.controller("UsuarioNuevoCtrl", function($scope, Usuario, Sitio, Perfil, TiposTelefono, TiposCorreo, Encriptar, $state) {
    $scope.init = function() {
        $scope.tiposTelefono = TiposTelefono;
        $scope.tiposCorreo = TiposCorreo;
        $scope.usuario = {telefonos: [], correos: [], perfiles: []};

        $scope.sitios = Sitio.query({}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
        $scope.perfiles = Perfil.query({}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.createUsuario = function() {
        if ($scope.usuario.clave) {
            var hash = Encriptar($scope.usuario.clave);
            $scope.usuario.clave = hash.toString();
        }
        var usuario = new Usuario($scope.usuario);
        usuario.$save({}, function() {
            $state.transitionTo("lista");
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.createTelefono = function() {
        $scope.usuario.telefonos.push({id: 0});
    };

    $scope.deleteTelefono = function(telefono) {
        $scope.usuario.telefonos.splice($scope.usuario.telefonos.indexOf(telefono), 1);
    };

    $scope.createCorreo = function() {
        $scope.usuario.correos.push({id: 0});
    };

    $scope.deleteCorreo = function(correo) {
        $scope.usuario.correos.splice($scope.usuario.correos.indexOf(correo), 1);
    };

    $scope.cancelar = function() {
        $state.transitionTo("lista");
    };

    $scope.init();
});

ctrl.controller("UsuarioEditarCtrl", function($scope, Usuario, Sitio, Perfil, TiposTelefono, TiposCorreo, Encriptar, $state, $stateParams) {
    $scope.init = function() {
        $scope.tiposTelefono = TiposTelefono;
        $scope.tiposCorreo = TiposCorreo;
        $scope.cambio = false;
        $scope.usuario = Usuario.get({id: $stateParams.usuarioId}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
        $scope.sitios = Sitio.query({}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
        $scope.perfiles = Perfil.query({}, function() {
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.cambioClave = function() {
        if (!$scope.cambio) {
            $scope.cambio = true;
            $scope.usuario.clave = "";
        }
    };

    $scope.updateUsuario = function() {
        if ($scope.usuario.clave && $scope.cambio) {
            var hash = Encriptar($scope.usuario.clave);
            $scope.usuario.clave = hash.toString();
        }
        var usuario = new Usuario($scope.usuario);
        usuario.$update({}, function() {
            $state.transitionTo("lista");
        }, function(error) {
            $scope.alertaError(error);
        });
    };

    $scope.createTelefono = function() {
        $scope.usuario.telefonos.push({id: 0});
    };

    $scope.deleteTelefono = function(telefono) {
        $scope.usuario.telefonos.splice($scope.usuario.telefonos.indexOf(telefono), 1);
    };

    $scope.createCorreo = function() {
        $scope.usuario.correos.push({id: 0});
    };

    $scope.deleteCorreo = function(correo) {
        $scope.usuario.correos.splice($scope.usuario.correos.indexOf(correo), 1);
    };

    $scope.cancelar = function() {
        $state.transitionTo("lista");
    };

    $scope.init();
});
