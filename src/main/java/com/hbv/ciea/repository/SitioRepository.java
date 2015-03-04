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
package com.hbv.ciea.repository;

import com.hbv.ciea.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositorio de Sitios.
 *
 * @author Herman Barrantes
 * @since 24-nov-2014
 * @see http://docs.spring.io/spring-data/jpa/docs/1.4.2.RELEASE/reference/html/jpa.repositories.html
 * @see http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 */
@Transactional(readOnly = true)
public interface SitioRepository extends JpaRepository<Sitio, Long> {

    /**
     * Actualiza el Nombre de un Sitio por su ID.
     *
     * @param nombre Nuevo nombre del Sitio.
     * @param id ID del Sitio.
     * @return Cantidad de filas afectadas.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Sitio s SET s.nombre = ?1 WHERE s.id = ?2")
    int updateNombreById(String nombre, long id);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE Sitio s WHERE s.id = ?1")
    public void delete(Long id);
    
}
