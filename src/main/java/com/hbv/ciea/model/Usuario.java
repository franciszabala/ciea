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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Herman Barrantes
 * @since 24-nov-2014
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Size(min = 9, max = 20)
    @Column(name = "identificacion")
    private String identificacion;
    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 15)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(name = "usuario")
    private String usuario;
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "clave")
    private String clave;
    @Size(max = 50)
    @Column(name = "puesto")
    private String puesto;
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Valid
    @JoinTable(name = "usuario_telefono", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_telefono", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telefono> telefonos;
    @Valid
    @JoinTable(name = "usuario_correo", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_correo", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Correo> correos;
    @Valid
    @JoinColumn(name = "id_sitio", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sitio sitio;
    @Valid
    @JoinTable(name = "usuario_perfil", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_perfil", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Perfil> perfiles;

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public Set<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario[ id=" + id + " ]";
    }

}
