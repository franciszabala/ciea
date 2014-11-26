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

import com.hbv.ciea.model.Perfil;
import com.hbv.ciea.model.Usuario;
import com.hbv.ciea.repository.UsuarioRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herman Barrantes
 * @since 26-nov-2014
 */
@Service
public class SecurityService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public SecurityService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || username.trim().isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioRepository.findByUsuario(username);
        if (usuario == null) {
            return null;
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Perfil perfil : usuario.getPerfilList()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perfil.getNombre());
            authorities.add(authority);
        }
        return new User(usuario.getUsuario(), usuario.getClave(), usuario.getActivo(), true, true, true, authorities);
    }

}
