/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAS_2016.envio;

//import ObligatorioAS_2016.entidades.EnvioEntity;
import ObligatorioAS_2016.entidades.EnvioEntity;
import com.google.gson.Gson;
import java.util.List;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo
 */
@Stateless
@LocalBean
public class EnvioBean {
    
    static Logger log = Logger.getLogger("FILE");
    
    
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/QueueCadete")
    private Queue queueCadete;
    
    @Resource(lookup = "jms/QueueEmisor")
    private Queue queueEmisor;
    
    @Resource(lookup = "jms/QueueReceptor")
    private Queue queueReceptor;
    
    
    @PersistenceContext
    private EntityManager em;
       
    @PostConstruct
    private void init() {
       // System.out.println("INSTANCIA ENVIO BEAN");
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
        List<EnvioEntity> list = em.createQuery("select e from EnvioEntity e").getResultList();
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
            MessageProducer productorDeMensajeCadete = session.createProducer(queueCadete);
            MessageProducer productorDeMensajeEmisor = session.createProducer(queueEmisor);
            MessageProducer productorDeMensajeReceptor = session.createProducer(queueReceptor);
         
            Message mensaje = session.createTextMessage("Cadete tiene un envio pendiente:" + unEnvio.toString());
            productorDeMensajeCadete.send(mensaje);
            mensaje = session.createTextMessage("Estimado cliente estamos realizado en envio:" + unEnvio.getId() + " sera entregado por: " + unEnvio.getCadete().toString());
            productorDeMensajeEmisor.send(mensaje);
            mensaje = session.createTextMessage("Estimado cliente el envio:"+ unEnvio.getId() + " sera entregado por: " + unEnvio.getCadete().toString());
            productorDeMensajeReceptor.send(mensaje);
            
            log.info("Envio realizado:" + unEnvio.toString());
            
        } catch (JMSException ex) {
            log.error("ERROR:"  + ex.getMessage() );
        }   
    }
}
