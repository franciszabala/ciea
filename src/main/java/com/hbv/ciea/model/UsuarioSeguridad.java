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
package com.hbv.ciea.model;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Herman Barrantes
 * @since 27-nov-2014
 */
public class UsuarioSeguridad extends Usuario implements UserDetails {

    /**
     * Copy-Constructor.
     *
     * @param usuario Usuario a copiar y extender.
     */
    public UsuarioSeguridad(Usuario usuario) {
        this.setId(usuario.getId());
        this.setIdentificacion(usuario.getIdentificacion());
        this.setNombre(usuario.getNombre());
        this.setPrimerApellido(usuario.getPrimerApellido());
        this.setSegundoApellido(usuario.getSegundoApellido());
        this.setUsuario(usuario.getUsuario());
        this.setClave(usuario.getClave());
        this.setActivo(usuario.getActivo());
        this.setPerfiles(usuario.getPerfiles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Perfil perfil : this.getPerfiles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perfil.getNombre());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getClave();
    }

    @Override
    public String getUsername() {
        return this.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getActivo();
    }

}
