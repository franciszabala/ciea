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
import com.hbv.ciea.dto.OrdenCompraDTO;
import com.hbv.ciea.dto.ProveedorDTO;
import com.hbv.ciea.model.OrdenCompra;
import com.hbv.ciea.model.Proveedor;
import static com.hbv.ciea.rest.ApiConstantes.*;
import com.hbv.ciea.service.OrdenCompraService;
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
@RequestMapping(API_ORDEN_COMPRA)
public class OrdenCompraRestController {

    @Autowired
    private OrdenCompraService ordenCompraService;

 
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<OrdenCompraDTO> listar() {
        return ordenCompraService.findAll();
    }


    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<OrdenCompraDTO> listar(Pageable pageable) {
        return ordenCompraService.findAll(pageable);
    }

   
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public OrdenCompra buscar(@PathVariable(ID) long id) {
        OrdenCompra ordenCompra = ordenCompraService.findOne(id);
        if (ordenCompra != null) {
            return ordenCompra;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

 
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrdenCompra nuevo(@RequestBody @Valid OrdenCompra ordenCompra) {
        return ordenCompraService.save(ordenCompra);
    }


    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrdenCompra editar(@PathVariable(ID) long id, @RequestBody @Valid OrdenCompra ordenCompra) {
        return ordenCompraService.update(ordenCompra);
    }


    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        ordenCompraService.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
