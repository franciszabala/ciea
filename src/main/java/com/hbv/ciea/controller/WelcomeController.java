/*
 * Copyright 2014 Codigo Fantasma.
 *
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit 
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, 
 * Mountain View, California, 94041, USA.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hbv.ciea.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

/**
 *
 * @author Herman Barrantes
 * @since 17-oct-2014
 */
@Controller
public class WelcomeController {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/", "/welcome"})
    public String welcome(@RequestParam(value = "nombre", required = false, defaultValue = "Herman") String nombre, Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("fecha", new Date());
        return "welcome";
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public ModelAndView getPdf() {
        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setJdbcDataSource(dataSource);
        view.setUrl("classpath:report.jrxml");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param1", "param1 value");
        view.setApplicationContext(appContext);
        return new ModelAndView(view, params);
    }
}
