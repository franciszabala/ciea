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
 * https://github.com/bijukunjummen/spring-boot-mvc-test/tree/withangular
 * http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/
 * http://draptik.github.io/blog/2013/07/28/restful-crud-with-angularjs/
 */
var app = angular.module("ordenCompraApp", ["ui.router", "ngResource", "ui.bootstrap", "alerta.controller", "modal.directiva", "orden_compra.service", "proveedor.service", "orden_compra.controller"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: "/ordenes_compra/lista",
                controller: 'OrdenCompraListaCtrl'
            })
            .state('editar', {
                url: '/editar/:ordenCompraId',
                templateUrl: "/ordenes_compra/editar",
                controller: 'OrdenCompraEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: "/ordenes_compra/nuevo",
                controller: 'OrdenCompraNuevoCtrl'
            });
});