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

import com.hbv.ciea.dto.ErrorRestDTO;
import com.hbv.ciea.dto.UsuarioDTO;
import com.hbv.ciea.model.Usuario;
import static com.hbv.ciea.rest.ApiConstantes.API_USUARIO;
import static com.hbv.ciea.rest.ApiConstantes.ID;
import static com.hbv.ciea.rest.ApiConstantes.ID_URL;
import static com.hbv.ciea.rest.ApiConstantes.PAGE_URL;
import com.hbv.ciea.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio Restful para Usuarios.
 *
 * @author Herman
 * @since 2015-01-22
 * @see http://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/
 * @see http://callistaenterprise.se/blogg/teknik/2014/04/15/a-first-look-at-spring-boot/
 */
@RestController
@RequestMapping(API_USUARIO)
@PreAuthorize("hasAnyAuthority('ADMIN','DIRECTOR')")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene la lista completa de Usuarios.
     *
     * @return Lista completa de Usuarios.
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UsuarioDTO> listar() {
        return usuarioService.findAll();
    }

    /**
     * Obtiene la lista paginada de Usuarios.
     *
     * @param pageable Paginación
     * @return Lista paginada de Usuarios.
     */
    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<UsuarioDTO> listar(Pageable pageable) {
        return usuarioService.findAll(pageable);
    }

    /**
     * Obtiene un Usuario por su ID.
     *
     * @param id ID del Usuario
     * @return Usuario correspondiente al ID
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Usuario buscar(@PathVariable(ID) long id) {
        Usuario usuario = usuarioService.findOne(id);
        if (usuario != null) {
            return usuario;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

    /**
     * Inserta un Usuario.
     *
     * @param usuario Usuario a Insertar
     * @return Usuario Insertado
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Usuario nuevo(@RequestBody @Valid Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /**
     * Edita un Usuario por su ID.
     *
     * @param id ID del Usuario
     * @param usuario Usuario a Actualizar
     * @return Usuario Actualizado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Usuario editar(@PathVariable(ID) long id, @RequestBody @Valid Usuario usuario) {
        return usuarioService.update(usuario);
    }

    /**
     * Borra un Usuario por su ID.
     *
     * @param id ID del Usuario
     * @return True si el Usuario fue borrado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        usuarioService.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
