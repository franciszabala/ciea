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

import com.hbv.ciea.dto.OrdenCompraDTO;
import com.hbv.ciea.model.OrdenCompra;
import com.hbv.ciea.model.Proveedor;
import com.hbv.ciea.repository.OrdenCompraRepository;
import com.hbv.ciea.repository.ProveedorRepository;
import com.hbv.ciea.util.CopyConstructorUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author salazaei
 */
@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private CopyConstructorUtil util;

    public List<OrdenCompraDTO> findAll() {
        return util.copiarLista(ordenCompraRepository.findAll(), OrdenCompraDTO.class);
    }

    public Page<OrdenCompraDTO> findAll(Pageable pageable) {
        Page<OrdenCompra> ordenes_compra = ordenCompraRepository.findAll(pageable);
        List<OrdenCompraDTO> compraDTO = util.copiarLista(ordenes_compra, OrdenCompraDTO.class);
        return new PageImpl<OrdenCompraDTO>(compraDTO, pageable, ordenes_compra.getTotalElements());
    }

    public OrdenCompra findOne(long id) {
        return ordenCompraRepository.findOne(id);
    }

    public OrdenCompra save(OrdenCompra ordenCompra) {
          if (ordenCompra.getProveedor() != null) {
            Proveedor proveedor = proveedorRepository.findOne(ordenCompra.getProveedor().getId());
            ordenCompra.setProveedor(proveedor);
        }
        return ordenCompraRepository.save(ordenCompra);
    }

    public OrdenCompra update(OrdenCompra ordenCompra) { 
        return ordenCompraRepository.save(ordenCompra);
    }

    public void delete(long id) {
        ordenCompraRepository.delete(id);
    }
}
