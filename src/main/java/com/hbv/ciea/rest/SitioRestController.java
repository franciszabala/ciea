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
import static com.hbv.ciea.rest.ApiConstantes.*;

import com.hbv.ciea.model.Sitio;
import com.hbv.ciea.repository.SitioRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio Restful para Sitios.
 *
 * @author Herman
 * @since 2014-12-13
 * @see
 * http://www.javacodegeeks.com/2014/05/spring-rest-controller-with-angularjs-resource.html
 * @see
 * http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
 */
@RestController
@RequestMapping(API_SITIO)
public class SitioRestController {

    @Autowired
    private SitioRepository sitioRepository;

    /**
     * Obtiene la lista completa de Sitios.
     *
     * @return Lista completa de Sitios.
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Sitio> listar() {
        return sitioRepository.findAll();
    }

    /**
     * Obtiene la lista paginada de Sitios.
     *
     * @param pageable Paginación
     * @return Lista paginada de Sitios.
     */
    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Page<Sitio> listar(Pageable pageable) {
        return sitioRepository.findAll(pageable);
    }

    /**
     * Obtiene un Sitio por su ID.
     *
     * @param id ID del Sitio
     * @return Sitio correspondiente al ID
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Sitio buscar(@PathVariable(ID) long id) {
        Sitio sitio = sitioRepository.findOne(id);
        if (sitio != null) {
            return sitio;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

    /**
     * Inserta un Sitio.
     *
     * @param sitio Sitio a Insertar
     * @return Sitio Insertado
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Sitio nuevo(@RequestBody @Valid Sitio sitio) {
        return sitioRepository.save(sitio);
    }

    /**
     * Edita un Sitio por su ID.
     *
     * @param id ID del Sitio
     * @param sitio Sitio a Actualizar
     * @return Sitio Actualizado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Transactional
    public Sitio editar(@PathVariable(ID) long id, @RequestBody @Valid Sitio sitio) {
        int actualizados = sitioRepository.updateNombreById(sitio.getNombre(), id);
        return sitio;
    }

    /**
     * Borra un Sitio por su ID.
     *
     * @param id ID del Sitio
     * @return True si el Sitio fue borrado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        sitioRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
