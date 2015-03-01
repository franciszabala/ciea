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

import com.hbv.ciea.dto.TomaFisicaDetalleDTO;
import com.hbv.ciea.model.Activo;
import com.hbv.ciea.model.ActivoEstado;
import com.hbv.ciea.model.EstadoTomaFisica;
import com.hbv.ciea.model.Sitio;
import com.hbv.ciea.model.TomaFisica;
import com.hbv.ciea.model.TomaFisicaDetalle;
import com.hbv.ciea.repository.ActivoRepository;
import com.hbv.ciea.repository.SitioRepository;
import com.hbv.ciea.repository.TomaFisicaDetalleRepository;
import com.hbv.ciea.repository.TomaFisicaRepository;
import com.hbv.ciea.util.CopyConstructorUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Servicio para el mantenimiento de Proveedores.
 *
 * @author Herman Barrantes
 * @since 14-ene-2015
 */
@Service
public class TomaFisicaDetalleService {

    @Autowired
    private TomaFisicaDetalleRepository tomaFisicaDetalleRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private TomaFisicaRepository tomaFisicaRepository;

    @Autowired
    private SitioRepository sitioRepository;

    @Autowired
    private CopyConstructorUtil util;

    public List<TomaFisicaDetalleDTO> findAll() {
        return util.copiarLista(tomaFisicaDetalleRepository.findAll(), TomaFisicaDetalleDTO.class);
    }

    public Page<TomaFisicaDetalleDTO> findAll(Pageable pageable) {
        Page<TomaFisicaDetalle> tomaFisicaDetalle = tomaFisicaDetalleRepository.findAll(pageable);
        List<TomaFisicaDetalleDTO> tomaFisicaDetalleDTO = util.copiarLista(tomaFisicaDetalle, TomaFisicaDetalleDTO.class);
        return new PageImpl<TomaFisicaDetalleDTO>(tomaFisicaDetalleDTO, pageable, tomaFisicaDetalle.getTotalElements());
    }

    public TomaFisicaDetalle findOne(long id) {
        return tomaFisicaDetalleRepository.findOne(id);
    }

    public TomaFisicaDetalle save(TomaFisicaDetalleDTO tomaFisicaDetalleDTO) {
        TomaFisicaDetalle tomaFisicaDetalle = tomaFisicaDetalleRepository.findTomaFisicaDetalle(tomaFisicaDetalleDTO.getTomaFisica().getId(), tomaFisicaDetalleDTO.getActivo().getId());
        if (tomaFisicaDetalle == null) {
            tomaFisicaDetalle = new TomaFisicaDetalle();
        }
        Activo activo = activoRepository.findOne(tomaFisicaDetalleDTO.getActivo().getId());
        tomaFisicaDetalle.setActivo(activo);
        tomaFisicaDetalle.setEstado(activo.getEstado());
        tomaFisicaDetalle.setSitio(activo.getSitio());
        TomaFisica tomaFisica = tomaFisicaRepository.findOne(tomaFisicaDetalleDTO.getTomaFisica().getId());
        tomaFisicaDetalle.setTomaFisica(tomaFisica);
        Sitio sitio = sitioRepository.findOne(tomaFisicaDetalleDTO.getActivo().getSitio().getId());
        activo.setSitio(sitio);
        activo.setEstado(ActivoEstado.valueOf(tomaFisicaDetalleDTO.getActivo().getEstado()));
        activo.setEstadoTomaFisica(EstadoTomaFisica.TERMINADO);
        return tomaFisicaDetalleRepository.save(tomaFisicaDetalle);
    }

    public TomaFisicaDetalle update(TomaFisicaDetalle tomaFisicaDetalle) {
        return tomaFisicaDetalleRepository.save(tomaFisicaDetalle);
    }

    public void delete(long id) {
        tomaFisicaDetalleRepository.delete(id);
    }

}
