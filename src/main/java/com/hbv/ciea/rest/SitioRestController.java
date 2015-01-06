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

import static com.hbv.ciea.rest.ApiConstantes.*;

import com.hbv.ciea.model.Sitio;
import com.hbv.ciea.repository.SitioRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
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

    @RequestMapping(method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public List<Sitio> listar() {
        return sitioRepository.findAll();
    }
//
//    @RequestMapping(method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
//    public Page<Sitio> listar(
//            @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int pagina,
//            @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int tamano) {
//        return sitioRepository.findAll(new PageRequest(pagina, tamano, Sort.Direction.ASC, ID));
//    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public Sitio buscar(@PathVariable(ID) long id) {
        return sitioRepository.findOne(id);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    public Sitio nuevo(@RequestBody @Valid Sitio sitio) {
        return sitioRepository.save(sitio);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    public Sitio editar(@RequestBody @Valid Sitio sitio) {
        int actualizados = sitioRepository.updateNombreById(sitio.getNombre(), sitio.getId());
        return sitio;
    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    public ResponseEntity borrar(@PathVariable(ID) long id) {
        sitioRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
