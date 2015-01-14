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
package com.hbv.ciea;

import java.util.List;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

/**
 *
 * @author Herman Barrantes
 * @since 25-nov-2014
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //Login
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/403").setViewName("403");
        //Directivas https://github.com/angular-ui/bootstrap/tree/master/template
        registry.addViewController("/template/alert/alert.html").setViewName("directivas/alert/alert");
        registry.addViewController("/template/pagination/pager.html").setViewName("directivas/pagination/pager");
        registry.addViewController("/template/pagination/pagination.html").setViewName("directivas/pagination/pagination");
        //Sitios
        registry.addViewController("/sitios").setViewName("sitio/inicio");
        registry.addViewController("/sitios/nuevo").setViewName("sitio/nuevo :: content");
        registry.addViewController("/sitios/editar").setViewName("sitio/editar :: content");
        registry.addViewController("/sitios/lista").setViewName("sitio/lista :: content");
        //Proveedores
        registry.addViewController("/proveedores").setViewName("proveedor/inicio");
        registry.addViewController("/proveedores/nuevo").setViewName("proveedor/nuevo :: content");
        registry.addViewController("/proveedores/editar").setViewName("proveedor/editar :: content");
        registry.addViewController("/proveedores/lista").setViewName("proveedor/lista :: content");

        registry.addViewController("/hello").setViewName("hello");
    }

    /*
     @see http://justinrodenbostel.com/2014/05/13/part-4-internationalization-in-spring-boot/
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    //http://blog.fawnanddoug.com/2012/05/pagination-with-spring-mvc-spring-data.html
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {

        PageableArgumentResolver resolver = new PageableArgumentResolver();
        resolver.setFallbackPagable(new PageRequest(1, 10));

        argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
    }
}
