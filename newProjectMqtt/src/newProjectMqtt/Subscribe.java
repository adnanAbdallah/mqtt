package newProjectMqtt;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



public class Subscribe implements MqttCallback {
	
	String user="root";
	String password="";
	
//	The broker url. 
	private static final    String brokerUrl = "tcp://broker.emqx.io:1883";
	

	/** The client id. */
	private static final String clientId = "clientId";

	/** The topic. */

	
	
	public void subscribe(String topic) {
		//	logger file name and pattern to log
		MemoryPersistence persistence = new MemoryPersistence();
		try
		{

			
			MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);

			System.out.println("checking");
			System.out.println("Mqtt Connecting to broker: " + brokerUrl);

			sampleClient.connect(connOpts);
			System.out.println("Mqtt Connected");

			sampleClient.setCallback(this);
			sampleClient.subscribe(topic);

			System.out.println("Subscribed");
			System.out.println("Listening");

		} catch (MqttException me) {
			System.out.println(me);
		}
	}

	 //Called when the client lost the connection to the broker
	public void connectionLost(Throwable arg0) {
		
	}

	
	
	//Called when a outgoing publish is complete
	public void deliveryComplete(IMqttDeliveryToken arg0) {

	}

	
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		

		
		System.out.println("| Topic:" + topic);
		System.out.println("| Message: " +message.toString());
		System.out.println("-------------------------------------------------");
		MyDbConnection db= new MyDbConnection();
		String s=message.toString();
		
		// Extract temperature and humidity values from the string
		String[] list = s.split(","); // split string by comma
		String temp = "", hum = "" ,cons="",status="";
		for (String item : list) {
		    if (item.startsWith("Temp:")) {
		        temp = item.substring(5); // extract temperature value
		    } else if (item.startsWith("Humidity:")) {
		        hum = item.substring(9); // extract humidity value
		        
		   
		    } else if (item.startsWith("Consumption:")) {
		    	cons =item.substring(12);

		    }else if (item.startsWith("Status:")) {
		    	status =item.substring(7);
		    }
		}
		
		boolean v=db.connectInsertDb(user,password,temp, hum,cons,status,topic);
		System.out.println(v);
	}
	
	
	
	public static void main(String[] args) {
		
		mainFrame frame =new mainFrame();
		 frame.setVisible(true);
	        System.out.println("the name is");
	        while (frame.isVisible()) {
	        	System.out.print(".");
	            // Wait for the user to input their name
	        }
	    		       
		String EspTopic=frame.mytopic;
//		EspTopic="6/7/8/9/10";


		new Subscribe().subscribe(EspTopic);

	}


}
