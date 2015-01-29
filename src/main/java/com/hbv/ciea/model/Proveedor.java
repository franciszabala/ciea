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

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Herman Barrantes
 * @since 24-nov-2014
 * @see
 * https://docs.jboss.org/hibernate/validator/4.0.1/reference/en/html/validator-usingvalidator.html
 * @see https://jersey.java.net/documentation/latest/bean-validation.html
 * @see http://spring.io/guides/gs/validating-form-input/
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement(name = "proveedor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proveedor", propOrder = {
    "id",
    "nombre",
    "direccion",
    "sitioWeb",
    "telefonos",
    "correos"
})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "sitio_web")
    private String sitioWeb;
    @Valid
    @JoinTable(name = "proveedor_telefono", joinColumns = {
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_telefono", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "telefonos")
    @XmlElement(name = "telefono")
    private List<Telefono> telefonos;
    @Valid
    @JoinTable(name = "proveedor_correo", joinColumns = {
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_correo", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "correos")
    @XmlElement(name = "correo")
    private List<Correo> correos;

    public Proveedor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Proveedor other = (Proveedor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proveedor[ id=" + id + " ]";
    }

}
