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
    $scope.usuario = {};
    $scope.selecionado = {};
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
        $scope.usuario = {id:0};
        $scope.formulario = true;
    };
    $scope.editar = function (usuario) {
        alert("Editar " + usuario.nombre);
//        $scope.selecionado = usuario;
        $scope.usuario = angular.copy(usuario);
        $scope.formulario = true;
    };
    $scope.borrar = function (index) {
        alert("Borrar " + index);
        $scope.usuarios.splice(index, 1);
        $scope.formulario = false;
    };
    $scope.aceptar = function (usuario) {
        alert("Aceptar");
        if(usuario.id == 0) {
            //Al enviar a la BD obtengo el ID y asi lo meto a la lista
            $scope.usuarios.push(usuario);
        } else {
//            $scope.selecionado = usuario;
            for(var i = 0; i < $scope.usuarios.length; i++){
                if($scope.usuarios[i].id == usuario.id){
                    $scope.usuarios.splice(i,1);
                }
            }
            $scope.usuarios.push(usuario);
        }
        $scope.formulario = false;
    };
    $scope.cancelar = function () {
        alert("Cancelar");
        $scope.formulario = false;
    };
}); 