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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Herman
 * @since 2014-12-16
 * @see http://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ocurrió un error inesperado")
public class ErrorRestDTO extends RuntimeException {

    private final String mensaje;
    private final String mensajeError;
    private final Object[] argumentos;

    public ErrorRestDTO(String mensaje) {
        this.mensaje = mensaje;
        this.mensajeError = mensaje;
        this.argumentos = null;
    }

    public ErrorRestDTO(String mensaje, Object[] argumentos) {
        this.mensaje = mensaje;
        this.mensajeError = mensaje;
        this.argumentos = argumentos;
    }

    public ErrorRestDTO(String mensaje, String mensajeError) {
        this.mensaje = mensaje;
        this.mensajeError = mensajeError;
        this.argumentos = null;
    }

    public ErrorRestDTO(String mensaje, String mensajeError, Object[] argumentos) {
        this.mensaje = mensaje;
        this.mensajeError = mensajeError;
        this.argumentos = argumentos;
    }

    public ErrorRestDTO(String mensaje, Exception ex) {
        this.mensaje = mensaje;
        this.mensajeError = ex.getMessage();
        this.argumentos = null;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public Object[] getArgumentos() {
        return argumentos;
    }

}
