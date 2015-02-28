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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Herman Barrantes
 * @since 24-nov-2014
 */
@Entity
@Table(name = "activo")
public class Activo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Size(max = 15)
    @Column(name = "placa")
    private String placa;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private ActivoEstado estado;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_toma_fisica")
    private ActivoEstadoTomaFisica estadoTomaFisica;
    @Valid
    @NotNull
    @JoinColumn(name = "id_sitio", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sitio sitio;
    @Valid
    @NotNull
    @JoinColumn(name = "id_articulo", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Articulo articulo;
    @Valid
    @JoinColumn(name = "id_detalle", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ActivoDetalle detalle;
    @Column(name = "habilitado")
    private boolean habilitado;

    public Activo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ActivoEstado getEstado() {
        return estado;
    }

    public void setEstado(ActivoEstado estado) {
        this.estado = estado;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ActivoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ActivoDetalle detalle) {
        this.detalle = detalle;
    }

    public ActivoEstadoTomaFisica getEstadoTomaFisica() {
        return estadoTomaFisica;
    }

    public void setEstadoTomaFisica(ActivoEstadoTomaFisica estadoTomaFisica) {
        this.estadoTomaFisica = estadoTomaFisica;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Activo other = (Activo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
