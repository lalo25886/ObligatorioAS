package ObligatorioAS_2016.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
   
   
   @Column(length = 300)
   private String descripcion;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "EMISOR_ID")
   private ClienteEntity emisor;
   
   @ManyToOne(fetch = FetchType.EAGER)
   private ClienteEntity receptor;
   
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "CADETE_ID")
  private CadeteEntity cadete;

   @ManyToOne(fetch = FetchType.EAGER)
   private VehiculoEntity vehiculo;
   
  
   @Column(length = 300)
   private String dirRetiro;
   
   @Column(length = 300)
   private String dirRecibo;

   public EnvioEntity() {
   }
    
   
   
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

   public ClienteEntity getReceptor() {
       return receptor;
   }

   public void setReceptor(ClienteEntity receptor) {
       this.receptor = receptor;
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
   
}