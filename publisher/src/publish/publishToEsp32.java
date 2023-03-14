package publish;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Scanner;
import java.lang.Math.*;

public class publishToEsp32 {

    public static void main(String[] args) {

        String topic;
        
        
      mainFrame frame = new mainFrame();
      frame.setVisible(true);
      while (frame.isVisible()) {
      	System.out.print(".");
          // Wait for the user to input their name
      }

      topic=frame.mytopic;
    	
//    	String topic="6/7/8/9/10";
  
        int qos =1;
        String broker = "tcp://broker.emqx.io:1883";
        String clientId     = MqttClient.generateClientId();
        MemoryPersistence persistence = new MemoryPersistence();
        char v=' ';

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected to broker");
            
            while(v!='x'&& v!='X') {
            	Scanner scanner =new Scanner(System.in);
            	
                String content;
                
                //publish only input 
                content=frame.mymessage;
//                content="on";
            	MqttMessage message = new MqttMessage(content.getBytes());
         	   message.setQos(qos);
                sampleClient.publish(topic, message);
                
                
            System.out.println("Message published");
            System.out.println("enter x to exit");
            String s = scanner.nextLine();
            v=s.charAt(0);

            }//end of while
            System.out.println("the end");
            sampleClient.disconnect();
            sampleClient.close();
            System.exit(0);
            
            } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
        }
    }



//Temp:44.46,Humidity:30.27Consumption:109.60,Status:1
    