package ObligatorioAS_2016.vehiculo;

import ObligatorioAS_2016.entidades.VehiculoEntity;
import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gonzalo
 */
@Stateless
@LocalBean
public class VehiculoBean {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
    }

    public VehiculoEntity agregar(VehiculoEntity v) {
        em.persist(v);
        return v;
   }
    public VehiculoEntity agregar(String body) {
       Gson gson = new Gson();
       VehiculoEntity v = gson.fromJson(body, VehiculoEntity.class);
       em.persist(v);
        return v;
    }

      public VehiculoEntity modificar(VehiculoEntity c) {
        em.merge(c);
        return c;
    }
    public VehiculoEntity modificar(Long id, String matriculaNueva) {
        VehiculoEntity v = em.find(VehiculoEntity.class, id);
        v.setMatricula(matriculaNueva);
        em.merge(v);
        return v;
    }

    public boolean eliminar(Long id) {
        VehiculoEntity v = em.find(VehiculoEntity.class, id);
        em.remove(v);
        return true;
    }
    public boolean eliminar(VehiculoEntity c) {
       VehiculoEntity aBorrar = em.find(VehiculoEntity.class, c.getId());
        em.remove(aBorrar);
        return true;
    }
    public List<VehiculoEntity> listar() {
        List<VehiculoEntity> list =
                em
                    .createQuery("select u from Vehiculo u")
                    .getResultList();
        return list;
    }

    public Vehiculo buscar(Long id) {
        VehiculoEntity ent = em.find(VehiculoEntity.class, id);
        Vehiculo v = new Vehiculo();
        v.setId(ent.getId());
        v.setMatricula(ent.getMatricula());
        return v;
    }

    public List<VehiculoEntity> buscar(String mat) {

        List<VehiculoEntity> listaVehiculo =
                em.createQuery("select v from VehiculoEntity v "
        + "where v.matricula = :matriculaIN")
                        .setParameter("matriculaIN", mat).getResultList();
        return listaVehiculo;
    }
}

