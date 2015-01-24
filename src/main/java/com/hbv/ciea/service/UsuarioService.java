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
package com.hbv.ciea.service;

import com.hbv.ciea.dto.UsuarioDTO;
import com.hbv.ciea.model.Usuario;
import com.hbv.ciea.repository.UsuarioRepository;
import com.hbv.ciea.util.CopyConstructorUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Servicio para el mantenimiento de Usuarios.
 *
 * @author Herman Barrantes
 * @since 22-ene-2015
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CopyConstructorUtil util;

    /**
     * Obtiene la lista completa de Usuarios.
     *
     * @return Lista completa de Usuarios.
     */
    public List<UsuarioDTO> findAll() {
        return util.copiarLista(usuarioRepository.findAll(), UsuarioDTO.class);
    }

    /**
     * Obtiene la lista paginada de Usuarios.
     *
     * @param pageable Paginación
     * @return Lista paginada de Usuarios.
     */
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioDTO> usuariosDTO = util.copiarLista(usuarios, UsuarioDTO.class);
        return new PageImpl<UsuarioDTO>(usuariosDTO, pageable, usuarios.getTotalElements());
    }

    /**
     * Obtiene un Usuario por su ID.
     *
     * @param id ID del Usuario
     * @return Usuario correspondiente al ID
     */
    public Usuario findOne(long id) {
        return usuarioRepository.findOne(id);
    }

    /**
     * Inserta un Usuario.
     *
     * @param usuario Usuario a Insertar
     * @return Usuario Insertado
     */
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Edita un Usuario.
     *
     * @param usuario Usuario a Actualizar
     * @return Usuario Actualizado
     */
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Borra un Usuario por su ID.
     *
     * @param id ID del Usuario
     */
    public void delete(long id) {
        usuarioRepository.delete(id);
    }

}
