
package ObligatorioAS_2016.cadete;

import ObligatorioAS_2016.usuario.Usuario;
import ObligatorioAS_2016.vehiculo.Vehiculo;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class Cadete extends Usuario{
    
    private List<Vehiculo> vehiculo;

    public List<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(List<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

 
    
}
