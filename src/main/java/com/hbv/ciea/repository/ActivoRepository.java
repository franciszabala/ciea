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

import com.hbv.ciea.model.Activo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de Activos.
 *
 * @author Herman Barrantes
 * @since 26-nov-2014
 */
public interface ActivoRepository extends JpaRepository<Activo, Long> {

    @Override
    @Query(value = "SELECT a FROM Activo a JOIN FETCH a.sitio JOIN FETCH a.articulo LEFT JOIN FETCH a.detalle", countQuery = "SELECT COUNT(a.id) FROM Activo a")
    Page findAll(Pageable pageable);

    @Override
    @Query("SELECT a FROM Activo a JOIN FETCH a.sitio JOIN FETCH a.articulo LEFT JOIN FETCH a.detalle")
    List<Activo> findAll();

}
