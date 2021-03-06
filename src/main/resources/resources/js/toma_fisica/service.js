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
var srvc = angular.module("toma_fisica.service", []);

srvc.factory("TomaFisica", function ($resource) {
    return $resource("/api/toma_fisica/:id:ruta", {id: "@id"}, {
        update: {
            method: 'PUT'
        },
        page: {
            method: 'GET',
            params: {ruta: 'page'}
        },
        iniciar: {
            method: 'PUT',
            params: {ruta: 'iniciar'}
        }

    });
});


srvc.factory("TomaFisicaDetalle", function ($resource) {
    return $resource("/api/toma_fisica_detalle/:id:ruta", {id: "@id"}, {
        update: {
            method: 'PUT'
        },
        page: {
            method: 'GET',
            params: {ruta: 'page'}
        }

    });
});

srvc.value("EstadoTomaFisica", ["EN_PROCESO", "TERMINADO"]);
srvc.value("ActivoEstado", ["EXCELENTE", "MUY_BUENO", "BUENO", "REGULAR", "MALO"]);

