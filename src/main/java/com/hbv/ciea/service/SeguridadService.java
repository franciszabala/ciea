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
package com.hbv.ciea.service;

import com.hbv.ciea.model.Usuario;
import com.hbv.ciea.model.UsuarioSeguridad;
import com.hbv.ciea.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herman Barrantes
 * @since 26-nov-2014
 * @see http://www.javacodegeeks.com/2014/03/springmvc4-spring-data-jpa-springsecurity-configuration-using-javaconfig.html
 */
@Service
public class SeguridadService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || username.trim().isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioRepository.findByUsuario(username);
        if (usuario == null) {
            return null;
        }
        return new UsuarioSeguridad(usuario);
    }

}
