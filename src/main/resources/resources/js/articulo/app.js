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

var app = angular.module("articuloApp", ["ui.router", "ngResource", "ui.bootstrap", "alerta.controller", "modal.directiva", "categoria.service", "articulo.service", "articulo.controller"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: "/articulos/lista",
                controller: 'ArticuloListaCtrl'
            })
            .state('editar', {
                url: '/editar/:articuloId',
                templateUrl: "/articulos/editar",
                controller: 'ArticuloEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: "/articulos/nuevo",
                controller: 'ArticuloNuevoCtrl'
            });
});

