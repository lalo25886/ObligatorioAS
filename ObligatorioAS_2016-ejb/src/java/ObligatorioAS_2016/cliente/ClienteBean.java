package ObligatorioAS_2016.cliente;

import ObligatorioAS_2016.entidades.ClienteEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author alvaro
 */
@Stateless
@LocalBean
public class ClienteBean {
    static Logger log = Logger.getLogger("FILE");
    @PersistenceContext
    private EntityManager em;
    @PostConstruct
    private void init() {
    }

    public ClienteEntity agregar(ClienteEntity unClienteEntity) {
        try {
            em.persist(unClienteEntity);
            return unClienteEntity;
        } catch (Exception e) {
            log.error("Error en agregar Cliente Entity: " + e.getMessage());
        }
         return null;
    }

    public ClienteEntity modificar(ClienteEntity unClienteEntity) {
        try {
            em.merge(unClienteEntity);
            return unClienteEntity;
        } catch (Exception e) {
             log.error("Error en eliminar Cliente Entity: " + e.getMessage());
        }
        return null;
    }

    public boolean eliminar(ClienteEntity unClienteEntity) {
        try {
            ClienteEntity aBorrar =
            em.find(ClienteEntity.class, unClienteEntity.getId());
            em.remove(aBorrar);
            return true;
        } catch (Exception e) {
             log.error("Error en eliminar Cliente Entity: " + e.getMessage());

        }
          return false;
    }

    public List<ClienteEntity> listar() {
        List<ClienteEntity> list =
                em.createQuery("select u from ClienteEntity u").getResultList();
        return list;
    }

    public Cliente buscar(Long id) {
        ClienteEntity ent = em.find(ClienteEntity.class, id);
        Cliente u = new Cliente();
        u.setId(ent.getId());
        u.setNombre(ent.getNombre());
        return u;
    }

    public List<ClienteEntity> buscar(String nombre) {
        List<ClienteEntity> listaCliente =
                em.createQuery("select u from ClienteEntity u "
                + "where u.nombre = :nombre")
                .setParameter("nombre", nombre).getResultList();
        return listaCliente;
    }

    public List<ClienteEntity> listarClientesEnvios() {
        List<ClienteEntity> listaClientes = em.createQuery("SELECT u "
                + "FROM ClienteEntity u",
               ClienteEntity.class).getResultList();
       return listaClientes;
   }


}
