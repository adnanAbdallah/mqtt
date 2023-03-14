package publish;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Scanner;
import java.lang.Math.*;

public class MqttPublisher {

    public static void main(String[] args) {

        String mytopic;
      mainFrame frame = new mainFrame();
      frame.setVisible(true);
      while (frame.isVisible()) {
      	System.out.print(".");
          // Wait for the user to input their name
      }

      mytopic=frame.mytopic;
    	
    	String [] topic=new String[7];
    	 topic[1] =mytopic;
    	 topic[2] ="873/110/45/68/12";
    	 topic[3] ="873/110/45/68/21";
    	 topic[4] ="873/110/45/68/22";
    	 topic[5] ="873/110/45/69/11";
         topic[6] ="873/110/45/69/12";
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

            	
                String [] content= new String[7];
                
                
                
                //publish only input 
                content[1]=frame.mymessage;
            	MqttMessage message1 = new MqttMessage(content[1].getBytes());
         	   message1.setQos(qos);
                sampleClient.publish(mytopic, message1);
                
                
                
                for(int i=2;i<=6;i++) {
                	float ranTemp=(float) (Math.random()*60)+30;
                	float ranHum=(float) (Math.random()*8)+25;
                	float cons=(float) (Math.random()*100)+100;
                	
                	String sranTemp = String.format("%.2f", ranTemp);
                	String sranHum = String.format("%.2f", ranHum);
                	String scons = String.format("%.2f", cons);

            	 content[i] ="Temp:"+sranTemp+",Humidity:"+sranHum+",Consumption:"+scons+",Status:1";
               
                 
                	MqttMessage message = new MqttMessage(content[i].getBytes());
                	   message.setQos(qos);
                       sampleClient.publish(topic[i], message);
                }
            
            
           
            
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
    