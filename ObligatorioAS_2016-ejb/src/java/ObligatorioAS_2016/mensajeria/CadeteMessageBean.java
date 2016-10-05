/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAS_2016.mensajeria;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
/**
 *
 * @author Gustavo
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/QueueCadete"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class CadeteMessageBean implements MessageListener {
   
    static Logger log = Logger.getLogger("FILE");
    
    public CadeteMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {

            TextMessage txt = (TextMessage) message;
            String msg = txt.getText();

               log.info("Mensaje del cadete recibido.");

        } catch (JMSException ex) {
            log.error("ERROR:"  + ex.getMessage() );
        }
  
    }
    
}