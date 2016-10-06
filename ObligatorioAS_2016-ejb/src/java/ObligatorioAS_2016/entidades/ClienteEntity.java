package ObligatorioAS_2016.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author alvaro
*/
@Entity
@XmlRootElement
public class ClienteEntity implements Serializable {

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
   private String apellido;

   @Column(length = 300)
   private String email;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "emisor")
   private List<EnvioEntity> listaEnvios;

   public Long getId() {
       return this.id;
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

   public String getNombre() {
       return this.nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getEmail() {
       return this.email;
   }

   public void setEmail(String email) {
       this.email = email;
   }
    public void setApellido(String apellido) {
       this.apellido = apellido;
   }
    public String getApellido() {
       return this.apellido;
   }

   public List<EnvioEntity> getListaEnvios() {
       return listaEnvios;
   }

   public void setListaEnvios(List<EnvioEntity> listaEnvios) {
       this.listaEnvios = listaEnvios;
   }


   @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null
               ?
               id.hashCode()
               : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {

       if (!(object instanceof ClienteEntity)) {
           return false;
       }
       ClienteEntity other = (ClienteEntity) object;
       if ((this.id == null
               && other.id != null)
               || (this.id != null
               && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }
}