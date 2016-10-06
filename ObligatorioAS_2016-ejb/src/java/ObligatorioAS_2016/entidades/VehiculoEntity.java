package ObligatorioAS_2016.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Gonzalo
*/
@Entity
@XmlRootElement
public class VehiculoEntity implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;


   @NotNull
   @Column(length = 300,unique = true)
   private String matricula;

   @NotNull
   @Column(length = 300)
   private String descripcion;

   @ManyToOne
   private CadeteEntity unCadete;

   public String getMatricula() {
       return matricula;
   }

   public void setMatricula(String matricula) {
       this.matricula = matricula;
   }

   public String getDescripcion() {
       return descripcion;
   }

   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public CadeteEntity getUnCadete() {
       return unCadete;
   }

   public void setUnCadete(CadeteEntity unCadete) {
       this.unCadete = unCadete;
   }
   

   @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null
               ?
               id.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       if (!(object instanceof VehiculoEntity)) {
           return false;
       }
       VehiculoEntity other = (VehiculoEntity) object;
       if ((this.id == null
               && other.id != null)
               || (this.id != null
               && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "Entidades.Vehiculo[ id=" + id + " ]";
   }
}
