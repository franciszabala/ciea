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
import com.hbv.ciea.model.Perfil;
import static com.hbv.ciea.rest.ApiConstantes.*;

import com.hbv.ciea.repository.PerfilRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio Restful para Perfil.
 *
 * @author Herman
 * @since 2015-01-24
 */
@RestController
@RequestMapping(API_PERFIL)
public class PerfilRestController {

    @Autowired
    private PerfilRepository perfilRepository;

    /**
     * Obtiene la lista completa de Perfiles.
     *
     * @return Lista completa de Perfiles.
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }
//
//    /**
//     * Obtiene la lista paginada de Perfiles.
//     *
//     * @param pageable Paginación
//     * @return Lista paginada de Perfiles.
//     */
//    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
//    public Page<Perfil> listar(Pageable pageable) {
//        return perfilRepository.findAll(pageable);
//    }
//
//    /**
//     * Obtiene un Perfil por su ID.
//     *
//     * @param id ID del Perfil
//     * @return Perfil correspondiente al ID
//     */
//    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
//    public Perfil buscar(@PathVariable(ID) long id) {
//        Perfil perfil = perfilRepository.findOne(id);
//        if (perfil != null) {
//            return perfil;
//        } else {
//            throw new ErrorRestDTO("error.not_found");
//        }
//    }
//
//    /**
//     * Inserta un Perfil.
//     *
//     * @param perfil Perfil a Insertar
//     * @return Perfil Insertado
//     */
//    @RequestMapping(method = {RequestMethod.POST}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
//    public Perfil nuevo(@RequestBody @Valid Perfil perfil) {
//        return perfilRepository.save(perfil);
//    }
//
//    /**
//     * Edita un Perfil por su ID.
//     *
//     * @param id ID del Perfil
//     * @param perfil Perfil a Actualizar
//     * @return Perfil Actualizado
//     */
//    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
//    @Transactional
//    public Perfil editar(@PathVariable(ID) long id, @RequestBody @Valid Perfil perfil) {
//        return perfilRepository.save(perfil);
//    }
//
//    /**
//     * Borra un Perfil por su ID.
//     *
//     * @param id ID del Perfil
//     * @return True si el Perfil fue borrado
//     */
//    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MEDIA_TYPE_JSON})
//    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
//        perfilRepository.delete(id);
//        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//    }

}
