package ObligatorioAS_2016.cadete;

import ObligatorioAS_2016.entidades.CadeteEntity;
import ObligatorioAS_2016.entidades.VehiculoEntity;
import ObligatorioAS_2016.vehiculo.Vehiculo;
import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
/**
 *
 * @author Gonzalo
 */
@Stateless
@LocalBean
public class CadeteBean {
  static Logger log = Logger.getLogger("FILE");
    
  @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADETE BEAN");
    }
    
    public CadeteEntity agregar(CadeteEntity unCadete) {
       em.persist(unCadete);
       return unCadete;
    }

    public CadeteEntity agregar(String body) {
       Gson gson = new Gson();
       CadeteEntity unCadete = gson.fromJson(body, CadeteEntity.class);
        em.persist(unCadete);
        return unCadete;
    }
    public CadeteEntity modificar(Long id, String nombreNuevo) {
        
        CadeteEntity unCadete = em.find(CadeteEntity.class, id);
        unCadete.setNombre(nombreNuevo);        
        em.merge(unCadete);        
        return unCadete;
    }
      public CadeteEntity modificar(CadeteEntity unCadete) {
        em.merge(unCadete);        
        return unCadete;
    }
     public boolean eliminar(CadeteEntity unCadete) {
       CadeteEntity aBorrar = em.find(CadeteEntity.class, unCadete.getId());
        em.remove(aBorrar);
        
        return true;
    }
    
    public boolean eliminar(Long id) {
        CadeteEntity unCadete = em.find(CadeteEntity.class, id);
        em.remove(unCadete);        
        return true;
    }
    
    public List<CadeteEntity> listar() {        
        List<CadeteEntity> list = 
                em
                    .createQuery("select u from CadeteEntity u")
                    .getResultList();
        
        return list;
    }
   
    public Cadete buscar(Long id) {
        CadeteEntity unCadeteEntity = em.find(CadeteEntity.class, id);
        Cadete unCadete = new Cadete();
        unCadete .setId(unCadeteEntity.getId());
        unCadete .setNombre(unCadeteEntity.getNombre());
        return unCadete ;
    }
    
    public List<CadeteEntity> buscar(String nombre) {
        List<CadeteEntity> listaCadetes = em.createQuery("select c from CadeteEntity c "
        + "where c.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return listaCadetes;
    }

    public boolean asociarCadeteVehiculo(Long cadeteId,Long vehiculoId) {
        
        try {
            CadeteEntity unCadeteEntity = em.find(CadeteEntity.class, cadeteId);
            VehiculoEntity unVehiculoEntity = em.find(VehiculoEntity.class, vehiculoId);
            unCadeteEntity.agregarVehiculoEntity(unVehiculoEntity);
            em.merge(unCadeteEntity);    
        } catch (Exception e) {
            System.out.println("Tengo un error en el asociar");
            System.out.println(e.toString());
            
            return false;
        }
        return true;
        
    }
   
    
}
