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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Herman
 * @since 2014-12-16
 */
public class ErrorValidacionDTO {

    private final List<ErrorCampoDTO> errores;

    public ErrorValidacionDTO() {
        errores = new ArrayList<ErrorCampoDTO>();
    }

    public void addErrorCampo(String campo, String mensaje) {
        ErrorCampoDTO error = new ErrorCampoDTO(campo, mensaje);
        errores.add(error);
    }

    public List<ErrorCampoDTO> getErrores() {
        return errores;
    }

}
