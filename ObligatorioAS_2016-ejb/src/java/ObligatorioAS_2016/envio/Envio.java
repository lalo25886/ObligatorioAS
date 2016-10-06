package ObligatorioAS_2016.envio;

import ObligatorioAS_2016.cadete.Cadete;
import ObligatorioAS_2016.cliente.Cliente;
import ObligatorioAS_2016.vehiculo.Vehiculo;
import java.util.Objects;

/**
*
* @author Gonzalo
*/
public class Envio {

private Long id;
private String descripcion;
private Cliente emisor;
private Cliente receptor;
private Cadete cadete;
private Vehiculo vehiculo;
private String dirRetiro;
private String dirRecibo;

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getDescripcion() {
       return descripcion;
   }

   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }

   public Cliente getEmisor() {
       return emisor;
   }

   public void setEmisor(Cliente emisor) {
       this.emisor = emisor;
   }

   public Cliente getRecepector() {
       return receptor;
   }

   public void setRecepector(Cliente recepector) {
       this.receptor = recepector;
   }

   public Cadete getCadete() {
       return cadete;
   }

   public void setCadete(Cadete cadete) {
       this.cadete = cadete;
   }

   public Vehiculo getVehiculo() {
       return vehiculo;
   }

   public void setVehiculo(Vehiculo vehiculo) {
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
       int hash = 7;
       hash = 53 * hash + Objects.hashCode(this.id);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       if (!(object instanceof Envio)) {
           return false;
       }
       Envio other = (Envio) object;
       return !((this.id == null
               && other.id != null)
               || (this.id != null
               && !this.id.equals(other.id)));
   }


   @Override
   public String toString() {
       return "Envio{" + "id=" + id + '}';
   }
}