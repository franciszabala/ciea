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
import com.hbv.ciea.dto.ErrorRestDTO;
import com.hbv.ciea.model.Proveedor;
import com.hbv.ciea.repository.ProveedorRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
 */
@RestController
@RequestMapping(API_PROVEEDOR)
public class ProveedorRestController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    /**
     * Obtiene la lista completa de Proveedores.
     *
     * @return Lista completa de Proveedores.
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    /**
     * Obtiene la lista paginada de Proveedores.
     *
     * @param pageable Paginación
     * @return Lista paginada de Proveedores.
     */
    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public Page<Proveedor> listar(Pageable pageable) {
        return proveedorRepository.findAll(pageable);
    }

    /**
     * Obtiene un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @return Proveedor correspondiente al ID
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MEDIA_TYPE_JSON})
    public Proveedor buscar(@PathVariable(ID) long id) {
        Proveedor proveedor = proveedorRepository.findOne(id);
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
    @RequestMapping(method = {RequestMethod.POST}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    public Proveedor nuevo(@RequestBody @Valid Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    /**
     * Edita un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @param proveedor Proveedor a Actualizar
     * @return Proveedor Actualizado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MEDIA_TYPE_JSON}, consumes = {MEDIA_TYPE_JSON})
    public Proveedor editar(@PathVariable(ID) long id, @RequestBody @Valid Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    /**
     * Borra un Proveedor por su ID.
     *
     * @param id ID del Proveedor
     * @return True si el Proveedor fue borrado
     */
    @RequestMapping(value = {ID_URL}, method = {RequestMethod.DELETE}, produces = {MEDIA_TYPE_JSON})
    public ResponseEntity<Boolean> borrar(@PathVariable(ID) long id) {
        proveedorRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}