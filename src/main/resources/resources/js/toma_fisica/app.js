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

var app = angular.module("tomaFisicaApp", ["ui.router", "ngResource", "ui.bootstrap", "alerta.controller", "modal.directiva","activo.service", "sitio.service","toma_fisica.service", "toma_fisica.controller"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("lista");

    $stateProvider
            .state('lista', {
                url: '/lista',
                templateUrl: "/toma_fisica/lista",
                controller: 'TomaFisicaListaCtrl'
            })
            .state('ejecutar', {
                url: '/editar/:tomaFisicaId',
                templateUrl: "/toma_fisica/editar",
                controller: 'TomaFisicaEditarCtrl'
            })
            .state('editar', {
                url: '/editar/:tomaFisicaId',
                templateUrl: "/toma_fisica/editar",
                controller: 'TomaFisicaEditarCtrl'
            })
            .state('nuevo', {
                url: '/nuevo',
                templateUrl: "/toma_fisica/nuevo",
                controller: 'TomaFisicaNuevoCtrl'
            });
});

