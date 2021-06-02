# FlexiCore Boot Integration Demo

This Repository demonstrates how to add FlexiCore Boot capabilities to an existing Spring Boot project.
The code has few branches, each presents additional capabilities, starting with a basic Spring application.

This example uses a database and is configured to use PostgreSQL database.

## Three different options for running this demo

- clone this repository and later in the demo the plugins repositories, this is the recommended way but it requires a manual installation of 

- use a pre-installed Docker from here

 

- use an installation file for Windows (here) and for Linux (here)



### note: this page is being edited, update will be available on 02-June-2021 noon time EST



# prerequisites 

- Java 11 JDK  , can use newer versions,  OpenJDK can be obtained from https://jdk.java.net/archive/

  - If you have another JDK installed, it can be used if >= version 11. However this demo was tested with version 11.

  - The installation in Windows requires adding an environment variable JAVA_HOME and the bin folder of the JDK to Windows path. you can use the tutorial [here:] (https://knowledge.exlibrisgroup.com/Aleph/Knowledge_Articles/How_to_Download_and_Install_OpenJDK_11_on_Windows_10_PC_for_Aleph)

  - [https://knowledge.exlibrisgroup.com/Aleph/Knowledge_Articles/How_to_Download_and_Install_OpenJDK_11_on_Windows_10_PC_for_Aleph]: 	"title"

    

    

  - test Java installation by typing (at the command line) *java -version*

- Apache Maven from here https://maven.apache.org/download.cgi

  - Maven is used here for building the artifacts, these can be built using an IDE like IntelliJ/Netbeans/Eclipse

- PostgreSQL database server from here: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

  



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
