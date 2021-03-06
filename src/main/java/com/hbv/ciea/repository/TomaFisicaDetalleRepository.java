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
import com.hbv.ciea.model.TomaFisicaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositorio de Toma Fisica.
 *
 * @author Eilyn Salazar
 */
public interface TomaFisicaDetalleRepository extends JpaRepository<TomaFisicaDetalle, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT t FROM TomaFisicaDetalle t where t.tomaFisica.id = :id_toma_fisica and t.activo.id = :id_activo")
    TomaFisicaDetalle findTomaFisicaDetalle(@Param("id_toma_fisica")long id_toma_fisica,@Param("id_activo") long id_activo);

}
