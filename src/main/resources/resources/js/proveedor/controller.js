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
var ctrl = angular.module("proveedor.controller", []);

ctrl.controller("ProveedorListaCtrl", function ($scope, Proveedor) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getProveedores();
    };

    $scope.getProveedores = function () {
        Proveedor.page({page: $scope.pagina}, function (data) {
            $scope.proveedores = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteProveedor = function (proveedor) {
        return Proveedor.delete({id: proveedor.id}, function () {
            $scope.alertaExito('Se borro correctamente');
            $scope.getProveedores();
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getProveedores();
    };

    $scope.init();
});

ctrl.controller("ProveedorNuevoCtrl", function ($scope, Proveedor, $state) {
    $scope.createProveedor = function () {
        var proveedor = new Proveedor($scope.proveedor);
        proveedor.$save({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

ctrl.controller("ProveedorEditarCtrl", function ($scope, Proveedor, TiposTelefono, TiposCorreo, $state, $stateParams) {
    $scope.init = function () {
        $scope.tiposTelefono = TiposTelefono;
        $scope.tiposCorreo = TiposCorreo;
        $scope.proveedor = Proveedor.get({id: $stateParams.proveedorId}, function () {
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.updateProveedor = function () {
        var proveedor = new Proveedor($scope.proveedor);
        proveedor.$update({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteTelefono = function (telefono) {
        $scope.proveedor.telefonos.splice($scope.proveedor.telefonos.indexOf(telefono), 1);
    };

    $scope.deleteCorreo = function (correo) {
        $scope.proveedor.correos.splice($scope.proveedor.correos.indexOf(correo), 1);
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };

    $scope.init();
});
