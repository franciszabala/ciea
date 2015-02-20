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

import com.hbv.ciea.dto.ActivoDTO;
import com.hbv.ciea.model.Activo;
import com.hbv.ciea.repository.ActivoRepository;
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
public class ActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private CopyConstructorUtil util;

    public List<ActivoDTO> findAll() {
        return util.copiarLista(activoRepository.findAll(), ActivoDTO.class);
    }

    public Page<ActivoDTO> findAll(Pageable pageable) {
        Page<Activo> activos = activoRepository.findAll(pageable);
        List<ActivoDTO> activoDTO = util.copiarLista(activos, ActivoDTO.class);
        return new PageImpl<ActivoDTO>(activoDTO, pageable, activos.getTotalElements());
    }

    public Activo findOne(long id) {
        return activoRepository.findOne(id);
    }

    public Activo save(Activo activo) {
        return activoRepository.save(activo);
    }

    public Activo update(Activo activo) {
        return activoRepository.save(activo);
    }

   
    public void delete(long id) {
        activoRepository.delete(id);
    }

}
