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
var ctrl = angular.module("orden_compra.controller", []);

ctrl.controller("OrdenCompraListaCtrl", function ($scope, OrdenCompra) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getOrdenesCompra();
    };

    $scope.showModal = function (id) {
        $scope.borrarId = id;
        $scope.alertaBorrado = false;
        $scope.modalBorrado = true;
    };

    $scope.hideModal = function () {
        $scope.modalBorrado = false;
    };

    $scope.getOrdenesCompra = function () {
        OrdenCompra.page({page: $scope.pagina}, function (data) {
            $scope.ordenes_compra = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteOrdenCompra = function () {
        $scope.modalBorrado = false;
        return OrdenCompra.delete({id: $scope.borrarId}, function () {
            $scope.alertaBorrado = true;
            $scope.getOrdenesCompra();
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getOrdenesCompra();
    };

    $scope.init();
});

ctrl.controller("OrdenCompraNuevoCtrl", function ($scope, OrdenCompra, OrdenCompraEstado, Proveedor, Articulo, $state) {
    $scope.init = function () {
        $scope.estados = OrdenCompraEstado;
        $scope.proveedores = Proveedor.query();
        $scope.articulos = Articulo.query();
        $scope.orden_compra = {detalles: []};
    };


    $scope.createOrdenCompra = function () {
        var orden_compra = new OrdenCompra($scope.orden_compra);
        orden_compra.$save({}, function () {
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

    $scope.createDetalle = function () {
        $scope.orden_compra.detalles.push({id: 0});
    };

    $scope.deleteDetalle = function (orden_compra_detalle) {
        $scope.orden_compra.detalles.splice($scope.orden_compra.detalles.indexOf(orden_compra_detalle), 1);
    };

    $scope.getTotal = function () {
        var cantidad_total = 0;
        var precio_total = 0;
        var total = 0;
        for (var i = 0; i < $scope.orden_compra.detalles.length; i++) {
            var detalle = $scope.orden_compra.detalles[i];
            cantidad_total += detalle.cantidad;
            precio_total += detalle.precio;
            total += (detalle.cantidad * detalle.precio)
        }
        return total;
    }

    $scope.init();
});

ctrl.controller("OrdenCompraEditarCtrl", function ($scope, OrdenCompra, OrdenCompraEstado, Proveedor, Articulo, $state, $stateParams) {
    $scope.init = function () {
        $scope.estados = OrdenCompraEstado;
        $scope.articulos = Articulo.query();
        $scope.orden_compra = OrdenCompra.get({id: $stateParams.ordenCompraId}, function () {
        }, function (error) {
            $scope.alertaError(error);
        });
        $scope.proveedores = Proveedor.query();
    };

    $scope.updateOrdenCompra = function () {
        var orden_compra = new OrdenCompra($scope.orden_compra);
        orden_compra.$update({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.createDetalle = function () {
        $scope.orden_compra.detalles.push({id: 0});
    };

    $scope.deleteDetalle = function (orden_compra_detalle) {
        $scope.orden_compra.detalles.splice($scope.orden_compra.detalles.indexOf(orden_compra_detalle), 1);
    };

    $scope.getTotal = function () {
        var cantidad_total = 0;
        var precio_total = 0;
        var total = 0;
        for (var i = 0; i < $scope.orden_compra.detalles.length; i++) {
            var detalle = $scope.orden_compra.detalles[i];
            cantidad_total += detalle.cantidad;
            precio_total += detalle.precio;
            total += (detalle.cantidad * detalle.precio)
        }
        return total;
    }

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };




    $scope.init();
});
