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
import java.io.Serializable;

/**
 *
 * @author salazaei
 */
public class ActivoTomaFisicaDTO implements Serializable {

    private final long id;
    private final String placa;
    private final String descripcion;
    private final String articulo;
    private final String estado;
    private final String estadoTomaFisica;
    private final Sitio sitio;

    public ActivoTomaFisicaDTO(Activo activo) {
        this.id = activo.getId();
        this.placa = activo.getPlaca();
        this.descripcion = activo.getDescripcion();
        this.articulo = activo.getArticulo().getDescripcion();
        this.estado = activo.getEstado().name();
        this.sitio = activo.getSitio();
        this.estadoTomaFisica = activo.getEstadoTomaFisica().name();
    }

    public ActivoTomaFisicaDTO() {
        this.id = 0;
        this.placa = "";
        this.descripcion = "";
        this.articulo = "";
        this.estado = "";
        this.estadoTomaFisica = "";
        this.sitio = new Sitio();
    }

    public long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getArticulo() {
        return articulo;
    }

    public String getEstado() {
        return estado;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public String getEstadoTomaFisica() {
        return estadoTomaFisica;
    }
 
}
