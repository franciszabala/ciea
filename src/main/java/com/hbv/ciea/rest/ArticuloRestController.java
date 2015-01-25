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
import com.hbv.ciea.model.Articulo;
import com.hbv.ciea.repository.ArticuloRepository;
import static com.hbv.ciea.rest.ApiConstantes.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(API_ARTICULO)
public class ArticuloRestController {

    @Autowired
    private ArticuloRepository articuloRepository;

    @RequestMapping(method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    private List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    private Page<Articulo> listar(Pageable pageable) {
        return articuloRepository.findAll(pageable);
    }
    
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    private Articulo buscar (@PathVariable(ID) long id){
        Articulo articulo = articuloRepository.findOne(id);
        if(articulo != null){
            return articulo;
        }else{
              throw new ErrorRestDTO("error.not_found");
        }
    }
    
    @RequestMapping(method = {RequestMethod.POST}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    private void nuevo(@RequestBody @Valid Articulo articulo){
        articuloRepository.save(articulo);
    }
    
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    private Articulo editar(@PathVariable(ID) long id, @RequestBody @Valid Articulo articulo){
        return articuloRepository.save(articulo);
    }
    
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MEDIA_TYPE_JSON})
    private void borrar(@PathVariable(ID) long id){
        articuloRepository.delete(id);
    }

}
