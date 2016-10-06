package ObligatorioAS_2016.cadete;

import ObligatorioAS_2016.entidades.CadeteEntity;
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
        //System.out.println("INSTANCIA CADETE BEAN");
    }

public CadeteEntity agregar(CadeteEntity unCadete) {
        try {
        em.persist(unCadete);
        } catch (Exception e) {
            log.error("Error al agregar:" + this.getClass().toString()
                    + e.getMessage());
            return null;
        }
        return unCadete;
    }

    public CadeteEntity agregar(String body) {
       try {
       Gson gson = new Gson();
       CadeteEntity unCadete = gson.fromJson(body, CadeteEntity.class);
        em.persist(unCadete);
        return unCadete;
        } catch (Exception e) {
            log.error("Error al agregar:" + this.getClass().toString()
                    + e.getMessage());
            return null;
        }
    }
    public CadeteEntity modificar(Long id, String nombreNuevo) {
        try {
            CadeteEntity unCadete = em.find(CadeteEntity.class, id);
            unCadete.setNombre(nombreNuevo);
            em.merge(unCadete);
            return unCadete;
        } catch (Exception e) {
            log.error("Error al modificar:" + this.getClass().toString()
                    + e.getMessage());
            return null;
        }
    }
      public CadeteEntity modificar(CadeteEntity unCadete) {
       try {
        em.merge(unCadete);
        return unCadete;
        } catch (Exception e) {
            log.error("Error al modificar:" + this.getClass().toString()
                    + e.getMessage());
            return null;
        }
    }
     public boolean eliminar(CadeteEntity unCadete) {
        try {
            CadeteEntity aBorrar = em.find(CadeteEntity.class,
                    unCadete.getId());
            em.remove(aBorrar);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar:" + this.getClass().toString()
                    + e.getMessage());
        }
        return false;
    }

    public boolean eliminar(Long id) {
        try {
        CadeteEntity unCadete = em.find(CadeteEntity.class, id);
        em.remove(unCadete);
        return true;
         } catch (Exception e) {
            log.error("Error al eliminar:" + this.getClass().toString()
                    + e.getMessage());
        }
        return false;
    }

    public List<CadeteEntity> listar() {        
        List<CadeteEntity> list =
                em.createQuery("select u from CadeteEntity u").getResultList();
        return list;
    }

    public Cadete buscar(Long id) {
        CadeteEntity unCadeteEntity = em.find(CadeteEntity.class, id);
        Cadete unCadete = new Cadete();
        unCadete .setId(unCadeteEntity.getId());
        unCadete .setNombre(unCadeteEntity.getNombre());
        return unCadete;
    }

    public List<CadeteEntity> buscar(String nombre) {
        List<CadeteEntity> listaCadetes =
        em.createQuery("select c from CadeteEntity c "
        + "where c.nombre = :nombre")
        .setParameter("nombre", nombre).getResultList();
        return listaCadetes;
    }
}
