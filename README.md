# FlexiCore Boot Integration Demo

This Repository demonstrates how to add FlexiCore Boot capabilities to an existing Spring Boot project.


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

    java -jar pet-server-2.0.0-exec.jar -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/
    
plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.
 ## How to Test?
 Swagger interface can be found in http://localhost:8080/swagger-ui.html
 
# Branch 3.0.0
[FlexiCore Boot Dynamic UI](https://support.wizzdi.com/#dynamic-user-interface) Capabilities Demo
 ## How to Run ?
run with spring boot properties launcher

    java -jar pet-server-3.0.0-exec.jar -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/
    
plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.

 ## How to Test?
 Swagger interface can be found in http://localhost:8080/FlexiCore/
 login is done using the admin@flexicore.com user this user password is show in a text file called firstRun.txt in /home/flexicore/
