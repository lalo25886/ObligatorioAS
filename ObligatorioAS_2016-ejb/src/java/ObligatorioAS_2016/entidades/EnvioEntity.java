
package ObligatorioAS_2016.entidades;

import ObligatorioAS_2016.cadete.Cadete;
import ObligatorioAS_2016.cliente.Cliente;
import ObligatorioAS_2016.vehiculo.Vehiculo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gonzalo
 */
@Entity
public class EnvioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(length = 300)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private ClienteEntity emisor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private ClienteEntity recepector;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private CadeteEntity cadete;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private VehiculoEntity vehiculo;
    
    @NotNull
    @Column(length = 300)
    private String dirRetiro;
    
    @NotNull
    @Column(length = 300)
    private String dirRecibo;
     
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getEmisor() {
        return emisor;
    }

    public void setEmisor(ClienteEntity emisor) {
        this.emisor = emisor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClienteEntity getRecepector() {
        return recepector;
    }

    public void setRecepector(ClienteEntity recepector) {
        this.recepector = recepector;
    }

    public CadeteEntity getCadete() {
        return cadete;
    }

    public void setCadete(CadeteEntity cadete) {
        this.cadete = cadete;
    }

    public VehiculoEntity getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDirRetiro() {
        return dirRetiro;
    }

    public void setDirRetiro(String dirRetiro) {
        this.dirRetiro = dirRetiro;
    }

    public String getDirRecibo() {
        return dirRecibo;
    }

    public void setDirRecibo(String dirRecibo) {
        this.dirRecibo = dirRecibo;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioEntity)) {
            return false;
        }
        EnvioEntity other = (EnvioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObligatorioAS_2016.entidades.EnvioEntity[ id=" + id + " ]";
    }
    
}
