package ObligatorioAS_2016.vehiculo;

import ObligatorioAS_2016.cadete.Cadete;

/**
 *
 * @author Gonzalo
 */
public class Vehiculo {
    private Long id;
    private String matricula;
    private String descripcion;
    private Cadete unCadete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String Matricula) {
        this.matricula = Matricula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public Cadete getUnCadete() {
        return unCadete;
    }

    public void setUnCadete(Cadete unCadete) {
        this.unCadete = unCadete;
    }
}
