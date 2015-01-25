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

import com.hbv.ciea.model.Articulo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de Artículos.
 *
 * @author Herman Barrantes
 * @since 26-nov-2014
 */
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    @Override
    @Query(value="SELECT a FROM Articulo a JOIN FETCH a.categoria", countQuery = "select count(a.id) from Articulo a")
    Page findAll(Pageable pageable);

    @Override
    @Query("SELECT a FROM Articulo a JOIN FETCH a.categoria")
    List<Articulo> findAll();
}
