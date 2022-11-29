# Notes about the implementation
## Domain model classes
The following classes are implemented as part of the model:
### TechService
  Service to be applied to a device, e.g. "Antivirus for Windows"
### TechServiceType
Type for grouping TechServices, e.g. "Antivirus"
### OperatingSystem
Enumeration with the values: WINDOWS, GNULINUX, MAC, ANY, OTHER. <br/>
These values are allowed for the operating system attribute in the classes
### Device
A device that could have different services assigned
### ServiceForDevice
TechService assigned for a device
### Order
Group of related ServiceForDevices
### Billing
Representation of the billing according to the requirement, generated from an order

## REST Endpoints

### Order
#### Create order
http://localhost:8080/order<br/>
Headers:<br/>
* Content-Type : application/json<br/>
Method: POST<br/>
Example response:
```json
{
    "id": 1
}
```
#### Get order
http://localhost:8080/order/{id}<br/>
Method: GET<br/>
Example:<br/>
http://localhost:8080/order/1<br/>
Example response body:
```json
{
    "id": 1
}
```
### Device
#### Create device
http://localhost:8080/device<br/>
Headers:<br/>
* Content-Type : application/json<br/>
Method: POST<br/>
Example request body:
```json
{
  "id" : "DEV1",
  "type" : "MAC",
  "operatingSystem" : "MAC"
}
```
Example response body:
```json
{
  "id": "DEV1",
  "systemName": null,
  "type": "MAC",
  "operatingSystem": "MAC"
}
```
#### Get device
http://localhost:8080/device/{id}<br/>
Method: GET<br/>
Example:<br/>
http://localhost:8080/order/DEV1<br/>
Example response body:
```json
{
  "id": "DEV1",
  "systemName": null,
  "type": "MAC",
  "operatingSystem": "MAC"
}
```
#### Delete device
http://localhost:8080/device/{id}<br/>
Method: DELETE<br/>
Example:<br/>
http://localhost:8080/order/DEV1<br/>
No content response
### Service Type
#### Get all services types
http://localhost:8080/service-type/all<br/>
Method: GET<br/>
Example response body:
```json
[
  {
    "name": "BACKUP",
    "description": "Backup"
  },
  {
    "name": "ANTIVIRUS",
    "description": "Antivirus"
  },
  {
    "name": "SCREEN_SHARE",
    "description": "Screen Share"
  },
  {
    "name": "SERVICE_DEVICE",
    "description": "Service Device"
  }
]
```
### Service
#### Create service
http://localhost:8080/service<br/>
Headers:<br/>
* Content-Type : application/json <br/>
Method: POST<br/>
Example request body:
```json
{
  "name" : "ANTIVIRUS_MAC",
  "price" : 5.0,
  "operatingSystem" : "MAC",
  "type" : "ANTIVIRUS"
}
```
Example response body:
```json
{
  "name": "ANTIVIRUS_MAC",
  "price": 5.0,
  "operatingSystem": "MAC",
  "type": {
    "name": "ANTIVIRUS",
    "description": "Antivirus"
  }
}
```
#### Get service
http://localhost:8080/service/{id}<br/>
Method: GET<br/>
Example:<br/>
http://localhost:8080/service/SERVICE_DEVICE<br/>
Example response body:
```json
{
  "name": "SERVICE_DEVICE",
  "price": 4.00,
  "operatingSystem": "ANY",
  "type": {
    "name": "SERVICE_DEVICE",
    "description": "Service Device"
  }
}
```
#### Delete device
http://localhost:8080/service/{id}<br/>
Method: DELETE<br/>
Example:<br/>
http://localhost:8080/service/ANTIVIRUS_MAC
No content response
### Service for device
#### Create service for device
http://localhost:8080/service-for-device<br/>
Headers:<br/>
* Content-Type : application/json<br/>
  Method: POST<br/>
  Example request body:
```json
{
  "serviceName" : "SERVICE_DEVICE",
  "deviceId" : "DEV1",
  "orderId" : 1
}
```
Example response body:
```json
{
  "id": 1,
  "priceApplied": 4.00,
  "type": {
    "name": "SERVICE_DEVICE",
    "description": "Service Device"
  },
  "deviceOS": "MAC",
  "deviceId": "DEV1",
  "serviceName": "SERVICE_DEVICE",
  "orderId": 1
}
```
#### Delete service for device
http://localhost:8080/service-for-device/{id}<br/>
Method: DELETE<br/>
Example:<br/>
http://localhost:8080/service-for-device/3<br/>
No content response
### Billing
#### Get Billing
http://localhost:8080/billing/{orderId}<br/>
Method: GET<br/>
Example:<br/>
http://localhost:8080/billing/1<br/>
Example response body:
```json
{
  "serviceDetails": [
    {
      "operatingSystem": "MAC",
      "serviceQuantity": {
        "TechServiceType{name='SERVICE_DEVICE', description='Service Device'}": 2,
        "TechServiceType{name='ANTIVIRUS', description='Antivirus'}": 1
      }
    }
  ],
  "priceDetails": [
    {
      "price": 8.00,
      "typeName": "SERVICE_DEVICE"
    },
    {
      "price": 5.0,
      "typeName": "ANTIVIRUS"
    }
  ],
  "total": 13.00
}
```

## Process Description
General process for creating a bill:
* Create devices as required
* Create services as required, there already exist some services in database, as specified. 
  "Service Device" is considered a service
* Create an order
* Assign services for devices in the order. 
* Generate Billing with order id

# Here starts original document

# NinjaOne Backend Interview Project

This project contains [Instructions](INSTRUCTIONS.md) that must be read in order to perform NinjaOne's code assessment.
Also the project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on
application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

## Starting the Application

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/sample/1
* http://localhost:8080/sample/2

You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Sample Repository in it.

Type:

```sql
SELECT * FROM SAMPLE;
````

Click `Run`, you should see two rows, for ids `1` and `2`

### Suggestions

Feel free to remove or repurpose the existing Sample Repository, Entity, Controller, and Service. 
