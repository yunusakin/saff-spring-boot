# saff-spring-boot
Store and Fetch Files project with Spring Boot
# what is SAFF ?
This is a spring boot project creates RESTfull API for store and fetch files (png, jpeg, jpg, docx, pdf, xlsx) which I called saff.
# Requirements
* Java 11
* Apache Maven 3.6.x

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
After run project you can see h2 database panel on this adress.
```
http://localhost:8282/saff/h2-panel
```
Also you can access swagger documentation ui on below link.
```
http://localhost:8282/saff/swagger-ui.html
```
