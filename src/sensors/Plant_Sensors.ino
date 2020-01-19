//moisture, humidity, rain, light, (camera)

//#include "SparkFunCCS811.h"
//#include "Wire.h"
#include <Servo.h>

//#define CCS811_ADDR 0x5B //Default I2C Address

const int soilAnaInput = A0; 
const int rainAnaInput = A1;
const int lightAnaInput = A2;
const int cameraDigOutput = 9;
const int servoDigOutput = 8;
//const int rainDigInput = 6;

//CCS811 mySensor(CCS811_ADDR);
Servo myservo;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  //pinMode(soilAnaInput, INPUT);
  pinMode(rainAnaInput, INPUT);
  pinMode(lightAnaInput, INPUT);
  pinMode(cameraDigOutput, OUTPUT);
  
  myservo.attach(servoDigOutput);

//  CCS811Core::status returnCode = mySensor.begin();
//
//  if (returnCode != CCS811Core::SENSOR_SUCCESS)
//  {
//    Serial.println(".begin() returned with an error.");
//    while (1); //Hang if there was a problem.
//  }
  digitalWrite(cameraDigOutput, HIGH);
} 

int soilAnaVal = 1;
int rainAnaVal = 1;
int C02Val = 1;
int TVOCVal = 1;
int lightAnaVal = 1;
int pos = 0;
int goOnceFlag = false;

double soilPercentage = 0;
double lightPercentage = 0;

void loop() {
  // put your main code here, to run repeatedly:
  //rainDigVal = digitalRead(rainDigInput);
  rainAnaVal = analogRead(rainAnaInput);
  soilAnaVal = analogRead(soilAnaInput); 
  lightAnaVal = analogRead(lightAnaInput);

  if (soilAnaVal >= 1000 && goOnceFlag) {
    for (pos = 0; pos <= 180; pos += 1) { // goes from 0 degrees to 180 degrees
      // in steps of 1 degree
      myservo.write(pos);              // tell servo to go to position in variable 'pos'
    }
    goOnceFlag = false;
  }
  
  else if (soilAnaVal < 1000 && !goOnceFlag) {
    for (pos = 180; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
      myservo.write(pos);              // tell servo to go to position in variable 'pos'
    }
    goOnceFlag = true;
  }
  
//  if (mySensor.dataAvailable()) {
//    mySensor.readAlgorithmResults();
//
//    C02Val = mySensor.getCO2();
//    TVOCVal = mySensor.getTVOC();
//  }

  if (rainAnaVal >= 900 && rainAnaVal <= 1023) {
    rainAnaVal = 1;
  }
  else {
    rainAnaVal = 2;
  }

  //percent_humidity = (-100/769)*(-640 + (-227132 + 769(value_A0))^.5);
  soilPercentage = (-100.0/769.0) * (-640.0 + pow(-227132.0 + (769.0*(double)soilAnaVal), 0.5)) + 35.0;
  lightPercentage = (-2.0/61.0) * ((double)lightAnaVal - 1109.0);
  
  digitalWrite(cameraDigOutput, LOW);

  delay(300);

  digitalWrite(cameraDigOutput, HIGH);

  //delay(5000);
  
//
//  Serial.print("Soil val: ");
//  Serial.print(soilVal);
//  Serial.println();
//
////  Serial.print("Rain dig: ");
////  Serial.print(rainDigVal);
////  Serial.print(", ");
//
//  Serial.print("Rain ana: ");
//  Serial.print(rainAnaVal);
//  Serial.println();
//  
//
////  Serial.print("Air: ");
////  Serial.print(C02Val);
////  Serial.print(" ");
////  Serial.print(TVOCVal);
////  Serial.print(", ");
////
////  Serial.println(value_A0);
////  Serial.println(percent_humidity);
//
//  Serial.println();

  Serial.println(""+(String)lightPercentage+", "+(String)soilPercentage+", "+(String)C02Val+", "+(String)TVOCVal+", "+(String)rainAnaVal+"");

  delay(1000);
  

}
