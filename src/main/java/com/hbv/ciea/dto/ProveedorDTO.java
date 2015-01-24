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

import com.hbv.ciea.model.Proveedor;
import java.io.Serializable;

/**
 *
 * @author Herman
 * @since 14/01/2015
 */
public class ProveedorDTO implements Serializable {

    private final long id;
    private final String nombre;
    private final String direccion;
    private final String sitioWeb;

    public ProveedorDTO(Proveedor proveedor) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.direccion = proveedor.getDireccion();
        this.sitioWeb = proveedor.getSitioWeb();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

}
