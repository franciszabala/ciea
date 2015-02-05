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
import java.io.Serializable;

/**
 *
 * @author salazaei
 */
public class ActivoDTO implements Serializable{
    
    private final long id;
    private final String placa;
    private final String serie;
    private final String articulo;
    private final String estado;
    private final String sitio;

    public ActivoDTO(Activo activo) {
        this.id = activo.getId();
        this.placa = activo.getPlaca();
        this.serie = activo.getSerie();
        this.articulo = activo.getArticulo().getDescripcion();
        this.estado = activo.getEstado().name();
        this.sitio = activo.getSitio().getNombre();
    }

    public long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getSerie() {
        return serie;
    }

    public String getArticulo() {
        return articulo;
    }  

    public String getEstado() {
        return estado;
    }

    public String getSitio() {
        return sitio;
    }
    
    
    
}
