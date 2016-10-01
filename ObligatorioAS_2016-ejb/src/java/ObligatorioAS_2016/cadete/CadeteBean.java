package ObligatorioAS_2016.cadete;

import ObligatorioAS_2016.entidades.CadeteEntity;
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
public class CadeteBean {

  @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADETE BEAN");
    }
       
    public CadeteEntity agregar(CadeteEntity u) {
        return agregar(u.getNombre());
    }

    public CadeteEntity agregar(String body) {
       Gson gson = new Gson();
       CadeteEntity u = gson.fromJson(body, CadeteEntity.class);
        em.persist(u);
        return u;
    }
    public CadeteEntity modificar(Long id, String nombreNuevo) {
        
        CadeteEntity u = em.find(CadeteEntity.class, id);
        u.setNombre(nombreNuevo);        
        em.merge(u);        
        return u;
    }
      public CadeteEntity modificar(CadeteEntity c) {
        em.merge(c);        
        return c;
    }
     public boolean eliminar(CadeteEntity c) {
       CadeteEntity aBorrar = em.find(CadeteEntity.class, c.getId());
        em.remove(aBorrar);
        
        return true;
    }
    
    public boolean eliminar(Long id) {
        CadeteEntity u = em.find(CadeteEntity.class, id);
        em.remove(u);        
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
        CadeteEntity ent = em.find(CadeteEntity.class, id);
        Cadete c = new Cadete();
        c.setId(ent.getId());
        c.setNombre(ent.getNombre());
        return c;
    }
    
    public List<CadeteEntity> buscar(String nombre) {
        List<CadeteEntity> list = em.createQuery("select c from CadeteEntity c "
        + "where c.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return list;
    }

}
