#Travel Agency REST API <img src="https://image.flaticon.com/icons/svg/2990/2990507.svg" width="50" height="50"> 
  
This is a mechanics for a web application made with Java 8 using the REST interface designed to support travellers in looking for an adventure and journey of their dreams. 
This project is a part of the Bootcamp: Java Plus Program at Kodilla.

## Requirements

[Java Development Kit (8 or higher)](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)

[MySQL](https://www.mysql.com/)
## Installation

-[x] Create a directory for the project and cd into it.

-[x] Clone down this repository:

```bash
git clone 
```
-[x] After dowloading the code to your local repository, build the project:

```bash
gradlew build
```
-[x] Create database in MySQL and user according to the data stored in the file [application.properties]():

```bash
spring.jpa.database=mysql
spring.datasource.url=jdbc:mysql://localhost:3306/${TRAVEL_DATABASE_NAME}?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true
spring.datasource.username=${TRAVEL_DATABASE_USER}
spring.datasource.password=${TRAVEL_DATABASE_PASSWORD}
```

...and that's it! Now you can run the project on your IDE and enjoy the functionalities that it gives you ;)

-[x] This API was also deployed on Heroku: https://desolate-sea-14021.herokuapp.com

-[x] Front End written with help of VAADIN Library can be found here: 

```bash
https://github.com/KariFra/Travel_Agency_VAADIN_FRONTEND.git
```
