/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria;

import java.util.logging.Level;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Gustavo
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Queue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ClienteMessageBean implements MessageListener {
    
    public ClienteMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            
            TextMessage txt = (TextMessage) message;
            
            String msg = txt.getText();
            
            System.out.println("holaaaa: " + msg);
            
        } catch (JMSException ex) {
             System.err.println(ex.getMessage());
           // Logger.getLogger(PintorMDB.class.getName()).log(Level.SEVERE, null, ex);
           //Alvaroooooooooooooo
        }
    }
    
}
