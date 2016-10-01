/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAS_2016.cliente;

import ObligatorioAS_2016.entidades.ClienteEntity;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvaro
 */
@Stateless
@LocalBean
public class ClienteBean {
    private static final Logger LOGGER= Logger.getLogger("logalvaro.log");
  
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/Queue")
    private Queue queue;
    
    
    @PersistenceContext
    private EntityManager em;
       
    @PostConstruct
    private void init() {
        //System.out.println("INSTANCIA CLIENTE BEAN");
    }
       
    public ClienteEntity agregar(ClienteEntity u) {
         em.persist(u);
          enviarCreacionCliente(u);
         return u;

    }
 
    public ClienteEntity agregar(String body) {
       Gson gson = new Gson();
       ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        
        em.persist(u);
        enviarCreacionCliente(u);
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
        
        List<ClienteEntity> list = 
                em
                    .createQuery("select u from ClienteEntity u")
                    .getResultList();
        
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
    private void enviarCreacionCliente(ClienteEntity unCliente) {
        
        try (Connection connection = connectionFactory.createConnection(); 
            Session session = connection.createSession()) {
            MessageProducer productorDeMensaje = session.createProducer(queue);
            Message mensaje = session.createTextMessage("Cliente creado:" + unCliente.getNombre());
            
            productorDeMensaje.send(mensaje);
            System.out.println("Cliente :" + unCliente.getNombre());
            LOGGER.log(Level.FINEST,"Prueba logger");
            
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
    }
    public void metodo() throws IOException {
    LogManager.getLogManager().readConfiguration(
        new FileInputStream("./log.properties"));
}

}
