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
var ctrl = angular.module("sitio.controller", []);

ctrl.controller("SitioListaCtrl", function ($scope, Sitio) {
    $scope.init = function () {
        $scope.limpiarAlertas();
        $scope.pagina = 1;
        $scope.getSitios();
    };

    $scope.getSitios = function () {
        Sitio.page({page: $scope.pagina}, function (data) {
            $scope.sitios = data;
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.deleteSitio = function (sitio) {
        return Sitio.delete({id: sitio.id}, function () {
            $scope.alertaExito('Se borro correctamente');
            $scope.getSitios();
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.pageChanged = function () {
        $scope.getSitios();
    };

    $scope.init();
});

ctrl.controller("SitioNuevoCtrl", function ($scope, Sitio, $state) {
    $scope.createSitio = function () {
        var sitio = new Sitio($scope.sitio);
        sitio.$save({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };
});

ctrl.controller("SitioEditarCtrl", function ($scope, Sitio, $state, $stateParams) {
    $scope.init = function () {
        $scope.sitio = Sitio.get({id: $stateParams.sitioId}, function () {
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.updateSitio = function () {
        var sitio = new Sitio($scope.sitio);
        sitio.$update({}, function () {
            $state.transitionTo("lista");
        }, function (error) {
            $scope.alertaError(error);
        });
    };

    $scope.cancelar = function () {
        $state.transitionTo("lista");
    };

    $scope.init();
});
