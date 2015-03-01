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
package com.hbv.ciea.dto;

import com.hbv.ciea.model.Activo;
import com.hbv.ciea.model.Sitio;
import com.hbv.ciea.model.TomaFisica;
import com.hbv.ciea.model.TomaFisicaDetalle;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author salazaei
 */
public class TomaFisicaDetalleDTO implements Serializable {

//    private final long id;
    @Valid
    @NotNull
    private final ActivoTomaFisicaDTO activo;
    @Valid
    @NotNull
    private final TomaFisica tomaFisica;


    public TomaFisicaDetalleDTO() {
        this.activo = new ActivoTomaFisicaDTO();
        this.tomaFisica = new TomaFisica();
    }

//    public long getId() {
//        return id;
//    }
    public ActivoTomaFisicaDTO getActivo() {
        return activo;
    }

    public TomaFisica getTomaFisica() {
        return tomaFisica;
    }

}
