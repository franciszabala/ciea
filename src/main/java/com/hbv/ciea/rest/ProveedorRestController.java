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
import com.hbv.ciea.dto.ProveedorDTO;
import com.hbv.ciea.model.Proveedor;
import static com.hbv.ciea.rest.ApiConstantes.API_PROVEEDOR;
import static com.hbv.ciea.rest.ApiConstantes.ID;
import static com.hbv.ciea.rest.ApiConstantes.ID_URL;
import static com.hbv.ciea.rest.ApiConstantes.PAGE_URL;
import com.hbv.ciea.service.ProveedorService;
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
 * Servicio Restful para Proveedores.
 *
 * @author Herman
 * @since 2015-01-13
 * @see http://spring.io/guides/tutorials/bookmarks/
 * @see http://spring.io/guides/gs/accessing-data-rest/
 * @see http://spring.io/guides/gs/rest-hateoas/
 * @see
 * http://patrickgrimard.com/2014/05/16/pagination-with-spring-data-and-hateoas-in-an-angularjs-app/
 * @see
 * http://blog.zenika.com/index.php?post/2012/06/15/HATEOAS-paging-with-Spring-MVC-and-Spring-Data-JPA
 */
@RestController
@RequestMapping(API_PROVEEDOR)
public class ProveedorRestController {

    @Autowired
    private ProveedorService proveedorService;

    /**
     * Obtiene la lista completa de Proveedores.
     *
     * @return Lista completa de Proveedores.
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ProveedorDTO> listar() {
        return proveedorService.findAll();
    }

    /**
     * Obtiene la lista paginada de Proveedores.
     *
     * @param pageable Paginación
     * @return Lista paginada de Proveedores.
     */
    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Page<ProveedorDTO> listar(Pageable pageable) {
        return proveedorService.findAll(pageable);
    }

    /**
     * Obtiene un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @return Proveedor correspondiente al ID
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Proveedor buscar(@PathVariable(ID) long id) {
        Proveedor proveedor = proveedorService.findOne(id);
        if (proveedor != null) {
            return proveedor;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

    /**
     * Inserta un Proveedor.
     *
     * @param proveedor Proveedor a Insertar
     * @return Proveedor Insertado
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Proveedor nuevo(@RequestBody @Valid Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    /**
     * Edita un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @param proveedor Proveedor a Actualizar
     * @return Proveedor Actualizado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Proveedor editar(@PathVariable(ID) long id, @RequestBody @Valid Proveedor proveedor) {
        return proveedorService.update(proveedor);
    }

    /**
     * Borra un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @return True si el Proveedor fue borrado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        proveedorService.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}