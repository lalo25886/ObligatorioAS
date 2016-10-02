/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAS_2016.envio;

//import ObligatorioAS_2016.entidades.EnvioEntity;
import ObligatorioAS_2016.entidades.EnvioEntity;
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
 * @author Gonzalo
 */
@Stateless
@LocalBean
public class EnvioBean {
    private static final Logger LOGGER= Logger.getLogger("logaGonzalo.log");
  
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/Queue")
    private Queue queue;
    
    
    @PersistenceContext
    private EntityManager em;
       
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA ENVIO BEAN");
    }
       
    public EnvioEntity agregar(EnvioEntity e) {
         em.persist(e);
         enviarCreacionEnvio(e);
         return e;

    }
 
    public EnvioEntity agregar(String body) {
       Gson gson = new Gson();
       EnvioEntity e = gson.fromJson(body, EnvioEntity.class);
        
        em.persist(e);
        enviarCreacionEnvio(e);
        return e;
    }
    
    public EnvioEntity modificar(EnvioEntity u) {
        
        em.merge(u);
        
        return u;
    }
    
    public boolean eliminar(EnvioEntity u) {
       EnvioEntity aBorrar = em.find(EnvioEntity.class, u.getId());
        em.remove(aBorrar);
        
        return true;
    }
    
    public List<EnvioEntity> listar() {
        
        List<EnvioEntity> list = 
                em
                    .createQuery("select e from EnvioEntity e")
                    .getResultList();
        
        return list;
    }
    
    public Envio buscar(Long id) {
        EnvioEntity ent = em.find(EnvioEntity.class, id);
        Envio e = new Envio();
        e.setId(ent.getId());
        e.setDescripcion(ent.getDescripcion());
        return e;
    }
    
    public List<EnvioEntity> buscar(String descripcion) {
        List<EnvioEntity> list = em.createQuery("select e from EnvioEntity e "
        + "where e.descripcion = :desc").setParameter("desc", descripcion).getResultList();
        return list;
    }
    private void enviarCreacionEnvio(EnvioEntity unEnvio) {
        
        try (Connection connection = connectionFactory.createConnection(); 
            Session session = connection.createSession()) {
            MessageProducer productorDeMensaje = session.createProducer(queue);
            Message mensaje = session.createTextMessage("Envio creado:" + unEnvio.getDescripcion());
            productorDeMensaje.send(mensaje);
            System.out.println("Envio :" + unEnvio.getDescripcion());
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
