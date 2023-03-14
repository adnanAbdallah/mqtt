//in this file the esp32 act as a publisher client 
//every 10 sec it publish the temp humi  consumption state to a broker 
//first we need to connect to a gateway router 
//to avoid the problem of configuration installation we can use the buid in bluetouth but this does not work due to high storage isse
//we use the DHT22 sensor in order to measure the temp and hum 
//unfortunatlly we don't use consumption sensor in practical work so we send a fix value to broker


#include <WiFi.h>
#include <PubSubClient.h>
#include <stdio.h>
#include <String.h>
#include <DHT.h>  // Including library for dht
// #include "BluetoothSerial.h"

// #if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
// #error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
// #endif

// BluetoothSerial SerialBT;

//temp and hum sensor
#define DHTPIN 4          //pin where the dht22 is connected
 
DHT dht(DHTPIN, DHT22);   //DHT22 is the type of sensor 

int const ledPin=18;
// WiFi
const char *ssid = "aaa"; // Enter your WiFi name
const char *password = "22222222";  // Enter WiFi password

// MQTT Broker
const char *mqtt_broker = "broker.emqx.io";
// const char *topic = "esp32/adnanAbdallah";
const char *topic = "1/2/3/4/5";
const char *mqtt_username = "emqx";
const char *mqtt_password = "public";
const int mqtt_port = 1883;


WiFiClient espClient;
PubSubClient client(espClient);

int status=1;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

void setup_wifi(){
 // connecting to a WiFi network
 WiFi.begin(ssid, password);

 while (WiFi.status() != WL_CONNECTED) {
     delay(500);
     Serial.println("Connecting to WiFi..");
 }

 Serial.println("Connected to the WiFi network");

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

void check_brokerComunication(){

  while (!client.connected()) {

     String client_id = "esp32-client-";
     client_id += String(WiFi.macAddress());
     Serial.printf("The client %s connects to the public mqtt broker\n", client_id.c_str());

    //  if (client.connect(client_id.c_str(), mqtt_username, mqtt_password)) {
       if (client.connect(client_id.c_str())) {
         Serial.println("Public emqx mqtt broker connected");
         client.subscribe("6/7/8/9/10");
     } else {
         Serial.print("failed with state ");
         Serial.print(client.state());
         delay(2000);
     }

 }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




void setup() {

  // Serial.begin(115200);
  // SerialBT.begin("ESP32test"); //Bluetooth device name
  // Serial.println("The device started, now you can pair it with bluetooth!");

 // Set software serial baud to 115200;
 Serial.begin(115200);

pinMode(ledPin, OUTPUT);
digitalWrite(ledPin, HIGH);
 // connecting to a WiFi network

 WiFi.begin(ssid, password);
 setup_wifi();


 //connecting to a mqtt broker
 client.setServer(mqtt_broker, mqtt_port);
 client.setCallback(callback);

 check_brokerComunication();


 delay(10);
 dht.begin();




}//end of setup

void callback(char *topic, byte *payload, unsigned int length) {
 Serial.print("Message arrived in topic: ");
 Serial.println(topic);
 Serial.print("Message:");
String message;

 for (int i = 0; i < length; i++) {
     Serial.print((char) payload[i]);
     message += (char)payload[i];
 }

 Serial.println();

   if (String(topic) == "6/7/8/9/10") {
    Serial.print("Changing output to ");
    if(message == "on"){
      Serial.println("on");
      status=1;
      digitalWrite(ledPin, HIGH);
    }
    else if(message == "off"){
      Serial.println("off");
      status=0;
      digitalWrite(ledPin, LOW);
    }
  }
}

void loop(){
 client.loop();
   float humVal = dht.readHumidity();
  float tempVal = dht.readTemperature();

  
  String str ="Temp:"+String(tempVal)+",Humidity:"+String(humVal)+",Consumption:30120,Status:"+String(status);
  
  client.publish(topic, str.c_str());
  Serial.println(str);
  delay(10000);
}
