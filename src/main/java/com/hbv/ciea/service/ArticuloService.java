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

import com.hbv.ciea.model.Articulo;
import com.hbv.ciea.model.Categoria;
import com.hbv.ciea.repository.ArticuloRepository;
import com.hbv.ciea.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Servicio para el mantenimiento de Artículos.
 *
 * @author Herman Barrantes
 * @since 2015/02/21
 */
@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }

    public Page<Articulo> findAll(Pageable pageable) {
        return articuloRepository.findAll(pageable);
    }

    public Articulo findOne(long id) {
        return articuloRepository.findOne(id);
    }

    public Articulo save(Articulo articulo) {
        if (articulo.getCategoria() != null) {
            Categoria categoria = categoriaRepository.findOne(articulo.getCategoria().getId());
            articulo.setCategoria(categoria);
        }
        return articuloRepository.save(articulo);
    }

    public Articulo update(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public void delete(long id) {
        articuloRepository.delete(id);
    }

}
