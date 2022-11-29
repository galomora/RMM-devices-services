# Notes about the implementation
## Domain model classes
The following classes are implemented as part of the model:
### TechService
  Service to be applied to a device, e.g. "Antivirus for Windows"
### TechServiceType
Type for grouping TechServices, e.g. "Antivirus"
### OperatingSystem
Enumeration with the values: WINDOWS, GNULINUX, MAC, ANY, OTHER. 
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
http://localhost:8080/order
Headers:
* Content-Type : application/json
Method: POST
Example response:
```json
{
    "id": 1
}
```
#### Get order
http://localhost:8080/order/{id}
Method: GET
Example
http://localhost:8080/order/1
Example response body:
```json
{
    "id": 1
}
```
### Device
#### Create device
http://localhost:8080/device
Headers:
* Content-Type : application/json
Method: POST
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
http://localhost:8080/device/{id}
Method: GET
Example
http://localhost:8080/order/DEV1
Example response body:
```json
{
  "id": "DEV1",
  "systemName": null,
  "type": "MAC",
  "operatingSystem": "MAC"
}
```


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
