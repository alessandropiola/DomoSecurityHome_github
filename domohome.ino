

// Load Wi-Fi library
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <FirebaseArduino.h>

#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 20, 4);

#include <NTPClient.h>
#include <WiFiUdp.h>

// Define NTP Client to get time
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP);

// Variables to save date and time
String formattedDate;
String dayStamp;
String timeStamp;

// Set these to run example.
#define FIREBASE_HOST "domosecurityhome.firebaseio.com"
#define FIREBASE_AUTH ""


// Replace with your network credentials
const char* ssid     = "INT5_2";
const char* password = "Gelato2Gusti";

// Set web server port number to 80
//WiFiServer server(80);
//scelgo il dispositivo
// 0 Nessuno
// 1 Luce tends
// 2 Tapparella 1
// 3 Tapparella 2
int setDispositivo = 0;
int oldVal = 0 ;
// Variable to store the HTTP request
String header;

// Auxiliar variables to store the current output state
String output5State = "off";
String output4State = "off";

bool backlightState = false;
uint32_t backlightStartTime = 0;

void setup() {
  Serial.begin(115200);

  // Initialize a NTPClient to get time
  timeClient.begin();
  // Set offset time in seconds to adjust for your timezone, for example:
  // GMT +1 = 3600
  // GMT +8 = 28800
  // GMT -1 = -3600
  // GMT 0 = 0
  timeClient.setTimeOffset(3600);

  lcd.begin(20,4);
  lcd.init();
    // Turn on the backlight.
  lcd.backlight();

  // Assign output variables to GPIO pins
  const int input16= 16;
  const int input14 = 14;
  const int input12 = 12;
  
  // Initialize the output variables as outputs
  pinMode(input16, INPUT);
  pinMode(input14, INPUT);
  pinMode(input12, INPUT);
  
  // Set outputs to LOW
  digitalWrite(input16, HIGH);
  digitalWrite(input14, HIGH);
  digitalWrite(input12, HIGH);

  // Connect to Wi-Fi network with SSID and password
  lcd.setCursor(0, 0);
  lcd.print("Wait ");
  lcd.setCursor(0, 1);
  lcd.print("Wifi");
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  // Print local IP address and start web server
  Serial.println("");
  Serial.println("WiFi connected.");
  lcd.setCursor(0, 0);
  lcd.print("WiFi");
  lcd.setCursor(0, 1);
  lcd.print("connected");
   
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  //server.begin();
  controllo();
  
}
void controllo(){
  


  //setto defoult tempo alzata tapparelle 
  HTTPClient httpPt;  //Declare an object of class HTTPClient
  httpPt.begin("http://192.168.1.202/cm?cmnd=PulseTime%20220");  //Specify request destination
  httpPt.setTimeout(20000);
  int httpCodePt = httpPt.GET();                                                                  //Send the request

  if (httpCodePt > 0) { //Check the returning code
    String payloadPt = httpPt.getString();   //Get the request response payload
    if(payloadPt.indexOf("on") > 0){
    }
    Serial.println(payloadPt);
    payloadPt.replace("{","");
    payloadPt.replace("PulseTime","Wait");
    payloadPt.replace("220","120sec");
    
    //Print the response payload
    lcd.setCursor(0, 0);
    lcd.print("Tempo Tapparella");
    lcd.setCursor(0, 1);
    lcd.print(payloadPt);
  }
  httpPt.end();
  delay(1500);
  httpPt.begin("http://192.168.1.202/cm?cmnd=PulseTime2%20220");  //Specify request destination
  httpPt.setTimeout(20000);
  httpCodePt = httpPt.GET();                                                                  //Send the request

  if (httpCodePt > 0) { //Check the returning code
    String payloadPt = httpPt.getString();   //Get the request response payload
    if(payloadPt.indexOf("on") > 0){
    }
    Serial.println(payloadPt);   //Print the response payload
    lcd.setCursor(0, 0);
    payloadPt.replace("{","");
    payloadPt.replace("PulseTime","Wait");payloadPt.replace("220","120sec");
    lcd.print("Tempo Tapparella");
    lcd.setCursor(0, 1);
    lcd.print(payloadPt);
  }
  httpPt.end();
  delay(1500);
  httpPt.begin("http://192.168.1.202/cm?cmnd=PulseTime3%20220");  //Specify request destination
  httpPt.setTimeout(20000);
  httpCodePt = httpPt.GET();                                                                  //Send the request

  if (httpCodePt > 0) { //Check the returning code
    String payloadPt = httpPt.getString();   //Get the request response payload
    if(payloadPt.indexOf("on") > 0){
    }
    Serial.println(payloadPt);   //Print the response payload
    payloadPt.replace("{","");
    payloadPt.replace("PulseTime","Wait");payloadPt.replace("220","120sec");
    lcd.setCursor(0, 0);
    lcd.print("Tempo Tapparella");
    lcd.setCursor(0, 1);
    lcd.print(payloadPt);
  }
  httpPt.end();
  delay(1500);
  httpPt.begin("http://192.168.1.202/cm?cmnd=PulseTime4%20220");  //Specify request destination
  httpPt.setTimeout(20000);
  httpCodePt = httpPt.GET();                                                                  //Send the request

  if (httpCodePt > 0) { //Check the returning code
    String payloadPt = httpPt.getString();   //Get the request response payload
    if(payloadPt.indexOf("on") > 0){
    }
    Serial.println(payloadPt);   //Print the response payload
    payloadPt.replace("{","");
    payloadPt.replace("PulseTime","Wait");payloadPt.replace("220","120sec");
    lcd.setCursor(0, 0);
    lcd.print("Tempo Tapparella");
    lcd.setCursor(0, 1);
    lcd.print(payloadPt);
  }
  httpPt.end();  
  delay(3000);
  
  while(!timeClient.update()) {
    timeClient.forceUpdate();
    lcd.setCursor(0, 0);
    lcd.print("Controllo       ");
    lcd.setCursor(0, 1);
    lcd.print("Data ORA        ");
  }
  // The formattedDate comes with the following format:
  // 2018-05-28T16:00:13Z
  // We need to extract date and time

  delay(1000);
  lcd.setCursor(0, 0);
  lcd.print("Controllo       ");
  lcd.setCursor(0, 1);
  lcd.print("     Periferiche");

  String ValLuceTenda = Firebase.getString("/Illuminazione/LuceTenda");
  Serial.println("Send+/Illuminazione/LuceTenda="+ValLuceTenda);
  //send request

  HTTPClient http;  //Declare an object of class HTTPClient
  http.begin("http://192.168.1.201/cm?cmnd=Power");  //Specify request destination
  http.setTimeout(20000);
  int httpCode = http.GET();                                                                  //Send the request

  if (httpCode > 0) { //Check the returning code
    String payload = http.getString();   //Get the request response payload
    if(payload.indexOf("on") > 0){

      output5State = "on";
      output4State = "on";      
    }
    Serial.println(payload);   //Print the response payload
    lcd.setCursor(0, 0);
    lcd.print("Stato LuceTenda");
    lcd.setCursor(0, 1);
    lcd.print(payload);
  }
  http.end();
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.stream("/Illuminazione");  

  lcd.clear();

  backlightStartTime = millis(); 
  backlightState = true; 


  
}

void loop(){
  
  
  //while(!timeClient.update()) {
  //timeClient.forceUpdate();
  //}
  // The formattedDate comes with the following format:
  // 2018-05-28T16:00:13Z
  // We need to extract date and time
  formattedDate = timeClient.getFormattedDate();
  //Serial.println(formattedDate);
  // Extract date
  int splitT = formattedDate.indexOf("T");
  dayStamp = formattedDate.substring(0, splitT);
  //Serial.print("DATE: ");
 // Serial.println(dayStamp);
  // Extract time
  timeStamp = formattedDate.substring(splitT+1, formattedDate.length()-1);
  //Serial.print("HOUR: ");
  //Serial.println(millis() - backlightStartTime);
  //delay(500);
  if ( backlightState == true && millis() - backlightStartTime >= 80000 )
  {
    backlightState = false;
    lcd.noBacklight(); // turn off backlight
    setDispositivo = 0;
  }
  if (digitalRead(12) == LOW || digitalRead(14) == LOW || digitalRead(16) == LOW ) {
    backlightStartTime = millis(); 
    backlightState = true; 
    lcd.backlight();
  }

  //controllo lo stato della scelta
  switch (setDispositivo) {
  case 0:
    // statements
    lcd.setCursor(0, 0);
    lcd.print("Scegli          ");
    lcd.setCursor(0, 1);
    //lcd.print("  Dispositivo.... ");
    //Serial.print("HOUR: ");
    lcd.print(timeStamp+"        ");


    break;
  case 1:
    // statements
    lcd.setCursor(0, 0);
    lcd.print("Dispositivo       ");
    lcd.setCursor(0, 1);
    lcd.print("Luce tenda        ");
    break;
  case 2:
    // statements
    lcd.setCursor(0, 0);
    lcd.print("Dispositivo       ");
    lcd.setCursor(0, 1);
    lcd.print("Tapparella 1      ");   
    break;
  case 3:
    lcd.setCursor(0, 0);
    lcd.print("Dispositivo       ");
    lcd.setCursor(0, 1);
    lcd.print("Tapparella 2      ");    // statements
    break;
  default:
    // statements
    break;
}

  

  
  //statepin16 = digitalRead(16);
  //Serial.print ("cambio          ");
  //Serial.print (digitalRead (16), DEC);
  //Serial.print (" val ");
  //Serial.println (setDispositivo, DEC);
  //Serial.print (" on " );
  //Serial.print (digitalRead (12), DEC);
  //Serial.print (" off " );
  //Serial.println (digitalRead (14), DEC);  
  
  
  //stato ON
  if (digitalRead(12) == LOW   && setDispositivo == 1 && oldVal == 0) {
    oldVal = 1 ;
    lcd.setCursor(0, 0);
    lcd.print("cambio          ");
    lcd.setCursor(4, 1);
    lcd.print("Stato         On");
    HTTPClient http;  //Declare an object of class HTTPClient
    http.begin("http://192.168.1.201/cm?cmnd=Power%20On");  //Specify request destination
    int httpCode = http.GET();                                                                  //Send the request
    if (httpCode > 0) { //Check the returning code
      String payload = http.getString();   //Get the request response payload
      Serial.println(payload);   //Print the response payload
      lcd.setCursor(0, 0);
      lcd.print("Acc LuceTenda   ");
      lcd.setCursor(0, 1);
      lcd.print(payload);      
      }
    http.end();
  } else if (digitalRead(12)  == HIGH && setDispositivo == 1 && oldVal == 1) {
    
    oldVal = 0;
  }
  //STATO OFF
  if (digitalRead(14) == LOW   && setDispositivo == 1 && oldVal == 0) {
    oldVal = 1 ;
    lcd.setCursor(0, 0);
    lcd.print("cambio          ");
    lcd.setCursor(4, 1);
    lcd.print("Stato        off");
    HTTPClient http;  //Declare an object of class HTTPClient
    http.begin("http://192.168.1.201/cm?cmnd=Power%20off");  //Specify request destination
    int httpCode = http.GET();                                                                  //Send the request
    if (httpCode > 0) { //Check the returning code
      String payload = http.getString();   //Get the request response payload
      Serial.println(payload);   //Print the response payload
      lcd.setCursor(0, 0);
      lcd.print("Spe LuceTenda");
      lcd.setCursor(0, 1);
      lcd.print(payload);
      }
    http.end();
  } else if (digitalRead(14)  == HIGH && setDispositivo == 1 && oldVal == 1) {
    
    oldVal = 0;
  }      
 
  //STATO ON ed off dopo 1minuto
  if (digitalRead(16) == LOW && oldVal == 0) {
    oldVal = 1 ;
    Serial.println("inc+"); 
    setDispositivo = setDispositivo+1;
    Serial.print("setDispositivo ");
    Serial.println(setDispositivo,DEC);
    if (setDispositivo == 4){
      setDispositivo = 0;
      Serial.println("azzero"); 
    }
    delay(1000);
  } else if (digitalRead(16)  == HIGH && oldVal == 1) {
    Serial.println("inc");
    oldVal = 0;
  }  



//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------TAPPARELLA 1----------TAPPARELLA 1--------------TAPPARELLA 1----------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //stato ON
  if (digitalRead(12) == LOW   && setDispositivo == 2 && oldVal == 0) {
    oldVal = 1 ;
    lcd.setCursor(0, 0);
    lcd.print("cambio          ");
    lcd.setCursor(4, 1);
    lcd.print("Stato         On");
    HTTPClient http;  //Declare an object of class HTTPClient
    http.begin("http://192.168.1.202/cm?cmnd=Power2%20off");  //Specify request destination
    int httpCode = http.GET();                                                                  //Send the request
    if (httpCode > 0) { //Check the returning code
      String payload = http.getString();   //Get the request response payload
      Serial.println(payload);   //Print the response payload
      lcd.setCursor(0, 0);
      lcd.print("Acc LuceTenda   ");
      lcd.setCursor(0, 1);
      lcd.print(payload); 
      
      HTTPClient http1;  //Declare an object of class HTTPClient
      http1.begin("http://192.168.1.202/cm?cmnd=Power%20On");  //Specify request destination
      int httpCode1 = http1.GET();                                                                  //Send the request
      if (httpCode1 > 0) { //Check the returning code
      String payload1 = http1.getString();   //Get the request response payload
      Serial.println(payload);   //Print the response payload
      lcd.setCursor(0, 0);
      lcd.print("Acc LuceTenda   ");
      lcd.print("Tapparella 1    ");
      lcd.setCursor(0, 1);
      lcd.print("in chiusura.....");      
        }
      http1.end();     
      }
    http.end();
    
  } else if (digitalRead(12)  == HIGH && setDispositivo == 2 && oldVal == 1) {
    
    oldVal = 0;
  }
  //STATO OFF
  if (digitalRead(14) == LOW   && setDispositivo == 2 && oldVal == 0) {
    oldVal = 1 ;
    lcd.setCursor(0, 0);
    lcd.print("cambio          ");
    lcd.setCursor(4, 1);
    lcd.print("Stato        off");
    HTTPClient http;  //Declare an object of class HTTPClient
    http.begin("http://192.168.1.202/cm?cmnd=Power%20off");  //Specify request destination
    int httpCode = http.GET();                                                                  //Send the request
    if (httpCode > 0) { //Check the returning code
      String payload = http.getString();   //Get the request response payload
      Serial.println(payload);   //Print the response payload
      lcd.setCursor(0, 0);
      lcd.print("Spe LuceTenda");
      lcd.setCursor(0, 1);
      lcd.print(payload);
      HTTPClient http3;  //Declare an object of class HTTPClient
      http3.begin("http://192.168.1.202/cm?cmnd=Power2%20On");  //Specify request destination
      int httpCode3 = http3.GET();                                                                  //Send the request
      if (httpCode3 > 0) { //Check the returning code
        String payload3 = http3.getString();   //Get the request response payload
        Serial.println(payload3);   //Print the response payload
        lcd.setCursor(0, 0);
        lcd.print("Spe LuceTenda");
        lcd.setCursor(0, 1);
        lcd.print(payload3);
        }
      http3.end();
      }
    http.end();
  } else if (digitalRead(14)  == HIGH && setDispositivo == 2 && oldVal == 1) {
    
    oldVal = 0;
  }  
/*
   if (Firebase.available()) {
     FirebaseObject event = Firebase.readEvent();
     String eventType = event.getString("type");
     eventType.toLowerCase();
     
     Serial.print("a="+eventType);
     
     Serial.print("event: ");
     Serial.println(eventType);
     if (eventType == "patch") {
        //String ValLuce = Firebase.getString("/Illuminazione/LuceFuori");
        //Serial.println("/Illuminazione/LuceFuori="+ValLuce);
        String ValLuceTenda = Firebase.getString("/Illuminazione/LuceTenda");
        if (ValLuceTenda == "ON") {
          HTTPClient http;  //Declare an object of class HTTPClient
          http.begin("http://192.168.1.201/cm?cmnd=Power%20On");  //Specify request destination
          int httpCode = http.GET();                                                                  //Send the request
          if (httpCode > 0) { //Check the returning code
          String payload = http.getString();   //Get the request response payload
          if(payload.indexOf("on") > 0){
            output5State = "on";
            output4State = "on";      
            }
          Serial.println(payload);   //Print the response payload
          lcd.setCursor(0, 0);
          lcd.print("Cambio LuceTenda");
          lcd.setCursor(0, 1);
          lcd.print(payload);
            }
          http.end();
          } else if (ValLuceTenda == "OFF") {
            HTTPClient http;  //Declare an object of class HTTPClient
            http.begin("http://192.168.1.201/cm?cmnd=Power%20off");  //Specify request destination
            int httpCode = http.GET();                                                                  //Send the request
            if (httpCode > 0) { //Check the returning code
            String payload = http.getString();   //Get the request response payload
            if(payload.indexOf("off") > 0){
              output5State = "off";
              output4State = "off";      
              }
            Serial.println(payload);   //Print the response payload
            lcd.setCursor(0, 0);
            lcd.print("Cambio LuceTenda");
            lcd.setCursor(0, 1);
            lcd.print(payload);
              }
            http.end();
 
          
        }
       
     }
     

   }
          
  WiFiClient client = server.available();   // Listen for incoming clients

  if (client) {                             // If a new client connects,
    Serial.println("New Client.");          // print a message out in the serial port
    String currentLine = "";                // make a String to hold incoming data from the client
    while (client.connected()) {            // loop while the client's connected
      if (client.available()) {             // if there's bytes to read from the client,
        char c = client.read();             // read a byte, then
        Serial.write(c);                    // print it out the serial monitor
        header += c;
        if (c == '\n') {                    // if the byte is a newline character
          // if the current line is blank, you got two newline characters in a row.
          // that's the end of the client HTTP request, so send a response:
          if (currentLine.length() == 0) {
            // HTTP headers always start with a response code (e.g. HTTP/1.1 200 OK)
            // and a content-type so the client knows what's coming, then a blank line:
            client.println("HTTP/1.1 200 OK");
            client.println("Content-type:text/html");
            client.println("Connection: close");
            client.println();
            
            // turns the GPIOs on and off
            if (header.indexOf("GET /5/on") >= 0) {
              Serial.println("GPIO 5 on");
              output5State = "on";
              digitalWrite(output5, HIGH);
              //send request
              HTTPClient http;  //Declare an object of class HTTPClient
              http.begin("http://192.168.1.201/cm?cmnd=Power%20On");  //Specify request destination
              int httpCode = http.GET();                                                                  //Send the request
              if (httpCode > 0) { //Check the returning code
                String payload = http.getString();   //Get the request response payload
                Serial.println(payload);                     //Print the response payload
                }
              http.end();   //Close connection
            } else if (header.indexOf("GET /5/off") >= 0) {
              Serial.println("GPIO 5 off");
              output5State = "off";
              //send request
              HTTPClient http;  //Declare an object of class HTTPClient
              http.begin("http://192.168.1.201/cm?cmnd=Power%20off");  //Specify request destination
              int httpCode = http.GET();                                                                  //Send the request
              if (httpCode > 0) { //Check the returning code
                String payload = http.getString();   //Get the request response payload
                Serial.println(payload);                     //Print the response payload
                }
              http.end();   //Close connection              digitalWrite(output5, LOW);
            } else if (header.indexOf("GET /4/on") >= 0) {
              Serial.println("GPIO 4 on");
              output4State = "on";
              digitalWrite(output4, HIGH);
              //send request
              HTTPClient http;  //Declare an object of class HTTPClient
              http.begin("http://192.168.1.201/cm?cmnd=Power%20On");  //Specify request destination
              int httpCode = http.GET();                                                                  //Send the request
              if (httpCode > 0) { //Check the returning code
                String payload = http.getString();   //Get the request response payload
                Serial.println(payload);                     //Print the response payload
                }
              http.end();   //Close connection              digitalWrite(output5, LOW);
            } else if (header.indexOf("GET /4/off") >= 0) {
              Serial.println("GPIO 4 off");
              output4State = "off";
              //send request
              HTTPClient http;  //Declare an object of class HTTPClient
              http.begin("http://192.168.1.201/cm?cmnd=Power%20off");  //Specify request destination
              int httpCode = http.GET();                                                                  //Send the request
              if (httpCode > 0) { //Check the returning code
                String payload = http.getString();   //Get the request response payload
                Serial.println(payload);                     //Print the response payload
                }
              http.end();   //Close connection              digitalWrite(output5, LOW);
              digitalWrite(output4, LOW);
            }
            
            // Display the HTML web page
            client.println("<!DOCTYPE html><html>");
            client.println("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            client.println("<link rel=\"icon\" href=\"data:,\">");
            // CSS to style the on/off buttons 
            // Feel free to change the background-color and font-size attributes to fit your preferences
            client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
            client.println(".button { background-color: #195B6A; border: none; color: white; padding: 16px 40px;");
            client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
            client.println(".button2 {background-color: #77878A;}</style></head>");
            
            // Web Page Heading
            client.println("<body><h1>Remote Control web server</h1>");
            
            // Display current state, and ON/OFF buttons for GPIO 5  
            client.println("<p>Luce Esterna Tenda - State " + output5State + "</p>");
            // If the output5State is off, it displays the ON button       
            if (output5State=="off") {
              client.println("<p><a href=\"/5/on\"><button class=\"button\">ON</button></a></p>");
            } else {
              client.println("<p><a href=\"/5/off\"><button class=\"button button2\">OFF</button></a></p>");
            } 
               
            // Display current state, and ON/OFF buttons for GPIO 4  
            client.println("<p>FREE - State " + output4State + "</p>");
            // If the output4State is off, it displays the ON button       
            if (output4State=="off") {
              client.println("<p><a href=\"/4/on\"><button class=\"button\">ON</button></a></p>");
            } else {
              client.println("<p><a href=\"/4/off\"><button class=\"button button2\">OFF</button></a></p>");
            }
            client.println("</body></html>");
            
            // The HTTP response ends with another blank line
            client.println();
            // Break out of the while loop
            break;
          } else { // if you got a newline, then clear currentLine
            currentLine = "";
          }
        } else if (c != '\r') {  // if you got anything else but a carriage return character,
          currentLine += c;      // add it to the end of the currentLine
        }
      }
    }
    // Clear the header variable
    header = "";
    // Close the connection
    client.stop();
    Serial.println("Client disconnected.");
    Serial.println("");
  }*/
}
