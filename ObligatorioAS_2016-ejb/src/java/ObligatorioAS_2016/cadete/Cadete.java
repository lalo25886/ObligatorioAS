
package ObligatorioAS_2016.cadete;

import ObligatorioAS_2016.usuario.Usuario;
import ObligatorioAS_2016.vehiculo.Vehiculo;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class Cadete extends Usuario{

    private List<Vehiculo> listaVehiculos;

    public List<Vehiculo> getVehiculo() {
        return listaVehiculos;
    }

    public void setVehiculo(List<Vehiculo> vehiculos) {
        this.listaVehiculos = vehiculos;
    }

    public void agregarVehiculo(Vehiculo unV){
        this.listaVehiculos.add(unV);
    }
}
