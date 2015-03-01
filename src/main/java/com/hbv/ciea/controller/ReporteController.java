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
package com.hbv.ciea.controller;

import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

/**
 *
 * @author Herman
 * @since 28/02/2015
 */
@Controller
public class ReporteController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/reporte/{reporte}/{formato}", method = RequestMethod.GET)
    public ModelAndView getRerporte(
            @PathVariable("reporte") String reporte,
            @PathVariable("formato") String formato,
            @RequestParam(required = false) Map<String, String> parametros) {
        Properties headers = new Properties();
        headers.put("Content-Disposition", "attachment; filename=" + reporte + "." + formato);
        JasperReportsMultiFormatView view = new JasperReportsMultiFormatView();
        view.setJdbcDataSource(dataSource);
        view.setUrl("classpath:" + reporte + ".jrxml");
        view.setHeaders(headers);
        parametros.put("format", formato);
        view.setApplicationContext(appContext);
        return new ModelAndView(view, parametros);
    }

}
