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

import com.hbv.ciea.model.Usuario;

/**
 *
 * @author Herman
 * @since 22/01/2015
 */
public class UsuarioDTO {

    private final long id;
    private final String identificacion;
    private final String nombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final String usuario;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.identificacion = usuario.getIdentificacion();
        this.nombre = usuario.getNombre();
        this.primerApellido = usuario.getPrimerApellido();
        this.segundoApellido = usuario.getSegundoApellido();
        this.usuario = usuario.getUsuario();
    }

    public long getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getUsuario() {
        return usuario;
    }

}
