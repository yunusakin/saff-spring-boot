# saff-spring-boot
Store and Fetch Files project with Spring Boot
# what is SAFF ?
This is a spring boot project creates RESTfull API for store and fetch files (png, jpeg, jpg, docx, pdf, xlsx) which I called saff.
# Requirements
* Java 11
* Apache Maven 3.8.x

# Build
Clone the source codes from Github.
```
https://github.com/yunusakin/saff-spring-boot.git
```
Open a terminal, and switch to the root folder of the project, and run the following command to build the whole project.
```
mvn clean install
```
# Run project
```
mvn spring-boot:run
```
After run project you'll see h2 database panel on following adress.
```
http://localhost:8282/saff/h2-panel
```
Also you'll access swagger documentation ui on below link.
```
http://localhost:8282/doc/swagger-ui.html
```
# Prerequisites before using APIs
* After run project and access h2 database panel you should insert user email and password credentials to USER table for get accessToken by following address. 
```
{server.address}/accesstoken 
```   
 ## Example : 
 + email --> testUser@saff.com 
 + password --> $2a$12$kWhQUqMGb5h9xLNut0fRN.VtcSTFOHT2ayFvk5QLFVz28kA.wv7CS 
   > **For password you should insert Bcrypt encrypted password to table.
    In this case password is "saffAb12" but encrypted password is "$2a$12$kWhQUqMGb5h9xLNut0fRN.VtcSTFOHT2ayFvk5QLFVz28kA.wv7CS"** 
   
* After insert user credentials you'll get access token and you can use it in other APIs (fileUpload,fetchAllfiles,fetchFile,deleteFile)
