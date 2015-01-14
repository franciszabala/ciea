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

import com.hbv.ciea.model.Correo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio de Correos.
 *
 * @author Herman Barrantes
 * @since 2015-01-13
 */
public interface CorreoRepository extends JpaRepository<Correo, Long> {

    @Query("select c from Proveedor p left join p.correos c where p.id = ?1")
    List<Correo> findByProveedorId(long id);

    @Query("select c from Usuario u left join u.correos c where u.id = ?1")
    List<Correo> findByUsuarioId(long id);

}
