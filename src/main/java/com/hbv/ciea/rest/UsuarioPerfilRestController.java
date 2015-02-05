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

import com.hbv.ciea.dto.UsuarioPerfilDTO;
import static com.hbv.ciea.rest.ApiConstantes.API_PERFIL;
import com.hbv.ciea.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio Restful para Usuarios.
 *
 * @author Herman
 * @since 2015-01-22
 * @see
 * http://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/
 * @see
 * http://callistaenterprise.se/blogg/teknik/2014/04/15/a-first-look-at-spring-boot/
 */
@RestController
@RequestMapping(API_PERFIL)
public class UsuarioPerfilRestController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene el Usuario actual.
     *
     * @return Usuario actual
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UsuarioPerfilDTO buscar() {
        return usuarioService.getUsuarioPerfil();
    }

    /**
     * Edita el Usuario actual.
     *
     * @param usuario Usuario a Actualizar
     * @return Usuario Actualizado
     */
    @RequestMapping(method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UsuarioPerfilDTO editar(@RequestBody @Valid UsuarioPerfilDTO usuario) {
        return usuarioService.updateUsuarioPerfil(usuario);
    }

}
