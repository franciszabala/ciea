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

import com.hbv.ciea.dto.ProveedorDTO;
import com.hbv.ciea.model.Proveedor;
import com.hbv.ciea.repository.ProveedorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herman Barrantes
 * @since 14-ene-2015
 */
@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorDTO> findAll() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        List<ProveedorDTO> proveedoresDTO = new ArrayList<ProveedorDTO>(proveedores.size());
        for (Proveedor proveedor : proveedores) {
            proveedoresDTO.add(new ProveedorDTO(proveedor));
        }
        return proveedoresDTO;
    }

    public Page<ProveedorDTO> findAll(Pageable pageable) {
        Page<Proveedor> proveedores = proveedorRepository.findAll(pageable);
        List<ProveedorDTO> proveedoresDTO = new ArrayList<ProveedorDTO>(proveedores.getSize());
        for (Proveedor proveedor : proveedores) {
            proveedoresDTO.add(new ProveedorDTO(proveedor));
        }
        return new PageImpl<ProveedorDTO>(proveedoresDTO, pageable, proveedores.getTotalElements());
    }

    public Proveedor findOne(long id) {
        return proveedorRepository.findOne(id);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void delete(long id) {
        proveedorRepository.delete(id);
    }

}
