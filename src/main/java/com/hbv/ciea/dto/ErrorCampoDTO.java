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
package com.hbv.ciea.dto;

/**
 *
 * @author Herman
 * @since 2014-12-16
 */
public class ErrorCampoDTO {

    private final String campo;

    private final String mensaje;

    public ErrorCampoDTO(String campo, String mensaje) {
        this.campo = campo;
        this.mensaje = mensaje;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensaje() {
        return mensaje;
    }

}
