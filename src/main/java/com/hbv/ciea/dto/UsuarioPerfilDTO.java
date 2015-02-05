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

import com.hbv.ciea.model.Correo;
import com.hbv.ciea.model.Telefono;
import com.hbv.ciea.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Herman
 * @since 05/02/2015
 */
public class UsuarioPerfilDTO {

    @NotNull
    @Size(min = 9, max = 20)
    private final String identificacion;
    @NotNull
    @Size(min = 3, max = 15)
    private final String nombre;
    @NotNull
    @Size(min = 3, max = 15)
    private final String primerApellido;
    @Size(max = 15)
    private final String segundoApellido;
    @Size(max = 50)
    private final String puesto;
    @Valid
    private final List<Telefono> telefonos;
    @Valid
    private final List<Correo> correos;

    public UsuarioPerfilDTO() {
        this.identificacion = "";
        this.nombre = "";
        this.primerApellido = "";
        this.segundoApellido = "";
        this.puesto = "";
        this.telefonos = new ArrayList<Telefono>();
        this.correos = new ArrayList<Correo>();
    }

    public UsuarioPerfilDTO(Usuario usuario) {
        this.identificacion = usuario.getIdentificacion();
        this.nombre = usuario.getNombre();
        this.primerApellido = usuario.getPrimerApellido();
        this.segundoApellido = usuario.getSegundoApellido();
        this.puesto = usuario.getPuesto();
        this.telefonos = usuario.getTelefonos();
        this.correos = usuario.getCorreos();
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

    public String getPuesto() {
        return puesto;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

}
