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

import com.hbv.ciea.model.Sitio;
import com.hbv.ciea.repository.SitioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        prueba();
    }
    
    public static void prueba() {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        SitioRepository repository = context.getBean(SitioRepository.class);

        // save a couple of customers
        repository.save(new Sitio("Dirección"));
        repository.save(new Sitio("Aula 1"));
        repository.save(new Sitio("Aula 2"));
        repository.save(new Sitio("Aula 3"));
        repository.save(new Sitio("Aula 4"));
        repository.save(new Sitio("Aula 5"));
        repository.save(new Sitio("Aula 6"));
        repository.save(new Sitio("Aula 7"));
        repository.save(new Sitio("Aula 8"));

        // fetch all customers
        Iterable<Sitio> sitios = repository.findAll();
        System.out.println("Sitios found with findAll():");
        System.out.println("----------------------------");
        for (Sitio sitio : sitios) {
            System.out.println(sitio);
        }
        System.out.println();

        // fetch an individual customer by ID
        Sitio sitio = repository.findOne(1L);
        System.out.println("Sitio found with findOne(1L):");
        System.out.println("-----------------------------");
        System.out.println(sitio);
        System.out.println();

        context.close();
    }

}
