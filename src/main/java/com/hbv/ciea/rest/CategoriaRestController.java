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
package com.hbv.ciea.rest;

import com.hbv.ciea.dto.ErrorRestDTO;
import com.hbv.ciea.model.Categoria;
import com.hbv.ciea.repository.CategoriaRepository;
import static com.hbv.ciea.rest.ApiConstantes.API_CATEGORIA;
import static com.hbv.ciea.rest.ApiConstantes.ID;
import static com.hbv.ciea.rest.ApiConstantes.ID_URL;
import static com.hbv.ciea.rest.ApiConstantes.PAGE_URL;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author salazaei
 */
@RestController
@RequestMapping(API_CATEGORIA)
public class CategoriaRestController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private Page<Categoria> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private Categoria buscar(@PathVariable(ID) long id) {
        Categoria categoria = categoriaRepository.findOne(id);
        if (categoria != null) {
            return categoria;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    private Categoria nuevo(@RequestBody @Valid Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    private Categoria editar(@PathVariable(ID) long id, @RequestBody @Valid Categoria categoria) {
        int actualizados = categoriaRepository.updateDescripcionById(categoria.getDescripcion(), id);
        return categoria;
    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        categoriaRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
