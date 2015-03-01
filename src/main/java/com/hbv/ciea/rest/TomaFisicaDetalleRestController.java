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
import com.hbv.ciea.dto.TomaFisicaDetalleDTO;
import com.hbv.ciea.model.TomaFisicaDetalle;
import com.hbv.ciea.repository.ActivoRepository;
import static com.hbv.ciea.rest.ApiConstantes.*;
import com.hbv.ciea.service.TomaFisicaDetalleService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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
@RequestMapping(API_TOMA_FISICA_DETALLE)
public class TomaFisicaDetalleRestController {

    @Autowired
    private TomaFisicaDetalleService tomaFisicaDetalleService;


    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private List<TomaFisicaDetalle> listar() {
//        return tomaFisicaDetalleRepository.findAll();
        return new ArrayList<TomaFisicaDetalle>();
    }

    @RequestMapping(value = {PAGE_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private Page<TomaFisicaDetalleDTO> listar(Pageable pageable) {
        return tomaFisicaDetalleService.findAll(pageable);
    }

    @RequestMapping(value = {ID_URL}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private TomaFisicaDetalle buscar(@PathVariable(ID) long id) {
        TomaFisicaDetalle tomaFisicaDetalle = tomaFisicaDetalleService.findOne(id);
        if (tomaFisicaDetalle != null) {
            return tomaFisicaDetalle;
        } else {
            throw new ErrorRestDTO("error.not_found");
        }
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    private TomaFisicaDetalle nuevo(@RequestBody @Valid TomaFisicaDetalleDTO tomaFisica) {
        return tomaFisicaDetalleService.save(tomaFisica);
    }

//    @RequestMapping(value = {ID_URL}, method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    private TomaFisicaDetalle editar(@PathVariable(ID) long id, @RequestBody @Valid TomaFisicaDetalleDTO tomaFisicaDetalle) {
//        return tomaFisicaDetalleService.save(tomaFisicaDetalle);
//    }
//
    @RequestMapping(method = {RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    private TomaFisicaDetalle guardar(@RequestBody @Valid TomaFisicaDetalleDTO tomaFisicaDetalleDTO) {;
        return tomaFisicaDetalleService.save(tomaFisicaDetalleDTO);
    }

}
