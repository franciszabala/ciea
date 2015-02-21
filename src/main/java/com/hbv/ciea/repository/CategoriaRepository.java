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

import com.hbv.ciea.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Herman Barrantes
 * @since 24-nov-2014
 */
@Transactional(readOnly = true)
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.descripcion = ?1 WHERE c.id = ?2")
    int updateDescripcionById(String descripcion, long id);
    
    @Override
    @Modifying
    @Transactional
    @Query("DELETE Categoria c WHERE c.id = ?1")
    public void delete(Long id);
}
