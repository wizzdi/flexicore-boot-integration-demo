# FlexiCore Boot Integration Demo

This Repository demonstrates how to add FlexiCore Boot capabilities to an existing Spring Boot project.
The code has few branches, each presents additional capabilities, starting with a basic Spring application.

### note: this page is being edited, update will be available on 02-June-2021 noon time EST



# prerequisites 

- Java 11 JDK  , can use newer versions, 

# Branch 1.0.0

 the initial spring boot application does not contain any FlexiCore Boot Dependency

 ## How to Run ?

run as any spring boot application:

    java -jar pet-server-1.0.0-exec.jar

 ## How to Test?

 Swagger interface can be found in http://localhost:8080/swagger-ui.html


# Branch 2.0.0

FlexiCore Boot Plugin Loading capabilities added , plugins were tested with the Person Service/Model and Library Service/Model found [here](https://github.com/wizzdi/FlexiCore-Examples)

 ## How to Run ?

run with spring boot properties launcher

    java -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/ -jar pet-server-2.0.0-exec.jar 

plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.

 ## How to Test?

 Swagger interface can be found in http://localhost:8080/swagger-ui.html , note that in the 2.0.0 fake authentication mechanism is used so a fake token should be provided when using swagger ( if the API requires a token).

# Branch 3.0.0

[FlexiCore Boot Dynamic UI](https://support.wizzdi.com/#dynamic-user-interface) Capabilities Demo

 ## How to Run ?

run with spring boot properties launcher

    java -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/ -jar pet-server-3.0.0-exec.jar 

plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.

 ## How to Test?

 Swagger interface can be found in http://localhost:8080/FlexiCore/
 login is done using the admin@flexicore.com user this user password is show in a text file called firstRun.txt in /home/flexicore/
