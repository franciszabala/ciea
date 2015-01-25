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
package com.hbv.ciea.rest;

/**
 *
 * @author Herman
 */
public class ApiConstantes {

    /* MediaType del servicio Rest */
    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String MEDIA_TYPE_XML = "application/xml";
    /* Constantes de URL */
    public static final String ID = "id";
    public static final String ID_URL = "/{id}";
    public static final String PAGE_URL = "/page";
    /* Constantes de Servicios Rest */
    public static final String API_SITIO = "/api/sitio";
    public static final String API_CATEGORIA = "/api/categoria";
    public static final String API_PROVEEDOR = "/api/proveedor";
    public static final String API_USUARIO = "/api/usuario";
    public static final String API_ARTICULO  = "/api/articulo";
}
