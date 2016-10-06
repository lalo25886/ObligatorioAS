package ObligatorioAS_2016.entidades;

import ObligatorioAS_2016.vehiculo.Vehiculo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Gonzalo
*/
@Entity
@XmlRootElement
public class CadeteEntity implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(length = 300,unique = true)
   private String ci;
   
   @NotNull
   @Column(length = 300)
   private String nombre;
   
   @Column(length = 300)
   private String email;
      
   @OneToMany
   private List<VehiculoEntity> listaVehiculos;
   
   @OneToMany(fetch = FetchType.EAGER, mappedBy = "cadete")
   private List<EnvioEntity> listaEnvios;
   
   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getCi() {
       return ci;
   }

   public void setCi(String ci) {
       this.ci = ci;
   }

   public List<VehiculoEntity> getListaVehiculos() {
       return listaVehiculos;
   }

   public void setListaVehiculos(List<VehiculoEntity> listaVehiculos) {
       this.listaVehiculos = listaVehiculos;
   }    
   
   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
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
       if (!(object instanceof CadeteEntity)) {
           return false;
       }
       CadeteEntity other = (CadeteEntity) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "Entidades.Cadete[ id=" + id + " ]";
   }

    public List<EnvioEntity> getListaEnvios() {
        return listaEnvios;
    }

    public void setListaEnvios(List<EnvioEntity> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }
   
   
}