/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAS_2016.cliente;

import ObligatorioAS_2016.entidades.ClienteEntity;
import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvaro
 */
@Stateless
@LocalBean
public class ClienteBean {
    
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("FILE");

    
    @PersistenceContext
    private EntityManager em;
       
    @PostConstruct
    private void init() {
        //System.out.println("INSTANCIA CLIENTE BEAN");
    }
       
    public ClienteEntity agregar(ClienteEntity u) {
        em.persist(u);
        return u;
    }
 
    public ClienteEntity agregar(String body) {
       Gson gson = new Gson();
       ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        em.persist(u);
        return u;
    }
    
    public ClienteEntity modificar(ClienteEntity u) {
        em.merge(u);
        return u;
    }
    
    public boolean eliminar(ClienteEntity u) {
       ClienteEntity aBorrar = em.find(ClienteEntity.class, u.getId());
        em.remove(aBorrar);
        return true;
    }
    
    public List<ClienteEntity> listar() {
        
        List<ClienteEntity> list = em.createQuery("select u from ClienteEntity u").getResultList();
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
        List<ClienteEntity> list = em.createQuery("select u from ClienteEntity u "
        + "where u.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return list;
    }

    public List<ClienteEntity> listarClientesEnvios() {
       List<ClienteEntity> listaClientes = em.createQuery("SELECT u FROM ClienteEntity u",ClienteEntity.class).getResultList();
       return listaClientes;
   }


}
