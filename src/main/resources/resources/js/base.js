/* 
 * Copyright 2014 Codigo Fantasma.
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

 var app = angular.module("baseApp", []);

 app.controller("baseController", function ($scope) {
    $scope.formulario = false;
    $scope.usuarios = [
    {id:1, nombre:'Hege',  apellido:"Pege" },
    {id:2, nombre:'Kim',   apellido:"Pim" },
    {id:3, nombre:'Sal',   apellido:"Smith" },
    {id:4, nombre:'Jack',  apellido:"Jones" },
    {id:5, nombre:'John',  apellido:"Doe" },
    {id:6, nombre:'Peter', apellido:"Pan" }
    ];

    $scope.nuevo = function () {
        alert("Nuevo");
        $scope.formulario = true;
        $scope.usuario = {id:0, nombre:"", apellido:""};
    };
    $scope.editar = function (id) {
        $scope.formulario = true;
    };
    $scope.borrar = function (id) {
        $scope.formulario = false;
    };
    $scope.aceptar = function () {
        alert("Aceptar");
        $scope.formulario = false;
    };
    $scope.cancelar = function () {
        alert("Cancelar");
        $scope.formulario = false;
    };
}); 