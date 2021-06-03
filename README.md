# FlexiCore Boot Integration Demo

This Repository demonstrates how to add FlexiCore Boot capabilities to an existing Spring Boot project.
The code has few branches, each presents additional capabilities, starting with a basic Spring application.

The basic application has no FlexiCore dependencies and is changed to include these capabilities, from that point on, the basic application is expanded via plugins, this include domain model changes, APIs and finally FlexiCore dynamic User interface support.

**Note**: the instructions here have been verified on clean Windows 10 virtual machine, step by step, however, if anything goes wrong, contact info@wizzdi.com for support.

This example uses a database and is configured to use PostgreSQL database.

## Two different options for running this demo

- clone this repository and later in the demo the plugins repositories, this is the recommended way but it requires a manual installation of 

- use a pre-installed Docker from here

  




# Prerequisites for Windows 

- Java 11 JDK  , can use newer versions,  OpenJDK can be obtained from [here](https://jdk.java.net/archive/)

  - If you have another JDK installed, it can be used if >= version 11. However this demo was tested with version 11.

  - The installation in Windows requires adding an environment variable JAVA_HOME and the bin folder of the JDK to Windows path.
  - you can use the [tutorial](https://knowledge.exlibrisgroup.com/Aleph/Knowledge_Articles/How_to_Download_and_Install_OpenJDK_11_on_Windows_10_PC_for_Aleph)
  - test Java installation by typing (at the command line) *java -version*

- Apache Maven from [here]( https://maven.apache.org/download.cgi)

  - Maven is used here for building the artifacts, these can be built using an IDE like IntelliJ/Netbeans/Eclipse.
  - Maven bin folder should be added to the path variable.
  - check by *mvn -version*  

- Install *git *  for Windows from [here]( https://git-scm.com/download/win)

- PostgreSQL database server from [here](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

  - You can uncheck the *stack builder * tools of the installer.

  - write down the PostgreSQL administrator password you choose.

  - you can either use PostgreSQL PGAdmin user interface or use PSQL at:

    - C:\Program Files\PostgreSQL\13\scripts\runpsql.bat   (the actual number is the major version of PostgreSQL, here version 13)

    - use enter at prompts, enter the password when prompted

    - then at PSQL command line, execute

      

    - ```bash
      create database pet;
      #will generate an error if the database 'pet' exists
      create user pet with password 'pet';
      #will generate an error if the user 'pet' exists
      GRANT ALL PRIVILEGES ON database pet to pet;
      ```


  - You can use Pgadmin to create the *pet *database **, create a user 'pet' with password 'pet', then grant all privileges on the pet database to the pet user.

 the initial spring boot application does not contain any FlexiCore Boot Dependency

# Branch 1.0.0

## How to build

at the command line, change the current directory to an empty directory of your choice, for example:

C:\Users\User\source>

Get the source from GitHub

```bash
git clone https://github.com/wizzdi/flexicore-boot-integration-demo
```

after cloning the folder you should see structure like this

```bash
├───flexicore-boot-integration-demo
│   └───src
│       └───main
│           ├───java
│           │   └───com
│           │       └───example
│           │           └───pet
│           │               ├───controller
│           │               ├───data
│           │               ├───model
│           │               ├───request
│           │               └───service
│           └───resources
```

for stage 1.0.0 we need to checkout this branch

​	 	

```bash
cd flexicore-boot-integration-demo
git checkout 1.0.0
```

we can now build the package using *maven*

```bash
mvn clean package
```

make sure you get something similar to:



*[INFO] BUILD SUCCESS*
*[INFO] ------------------------------------------------------------------------*
*[INFO] Total time:  10.718 s*
*[INFO] Finished at: 2021-06-02T11:30:15+03:00*

The first time you build can take few minutes for all dependencies to be fetched from online repositories.



 ## How to Run ?

run as any spring boot application:

    java -jar target/pet-server-1.0.0-exec.jar



 ## How to Test?

 Swagger interface can be found in http://localhost:8080/swagger-ui.html
You should see, after a while, an interface for testing the API endpoints

You can test the APIs using the 'try it out' button, try the *PUT*  API and create a pet. Then use the *GET* API to get created pets instances.

<img src="https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/first%20stage-1.jpg?raw=true" alt="first stage-1.jpg" style="zoom:50%;" />

# Branch 2.0.0

We will now show how plugins and entities jar can be added to pre-designated or configured folder to an existing Spring Boot app.

For this we need to add to the Spring Boot Application the required FlexiCore dependencies, this is done once.  Then the life cycle of a system will be 100% in plugins.

Checkout the Spring app to version 2.00 so it will include the required FlexiCore dependencies.

## FlexiCore boot modules added



We need to use the 2.0.0 branch to add FlexiCore capabilities

```bash
cd ~/source/flexicore-boot-integration-demo
git checkout 2.0.0
mvn clean package
```

make sure build is successful and proceed to testing the system.

 ## How to Test?

run with spring boot properties launcher

```bash
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar pet-server-2.0.0-exec.jar
#the '' are required when using PowerShell on Windows
```

    wait till the server starts and access the API via Swagger:

usually the last line in the output when server is ready is (number of beans may differ) : **total of 319 beans**

```
http://localhost:8080/swagger-ui.html
```

![first stage.jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/first%20stage.jpg?raw=true)

You can now see that many APIs endpoints have been added to the system, these are FlexiCore boot APIs 

## Building Plugins, adding plugins to app (no changes in the Spring Application)



files found here: https://github.com/wizzdi/FlexiCore-Examples

We will first add just the *Person* model and service. 

As Person service depends on Person model, we have to build and **install** it first.

### clone the repository

  	this is the folder for the source in our case, make sure that you are not inside *flexicore-boot-integration-demo* folder.

```bash
cd ~/source
git clone https://github.com/wizzdi/FlexiCore-Examples
cd FlexiCore-Examples
tree 
├───hello-world-rest
│  
├───hello-world-service
│   
├───library-model
│   └───src
│       └───main
│           ├───java
│           │   └───com
│           │       └───flexicore
│           │           └───example
│           │               └───library
│           │                   └───model
│           └───resources
├───library-service
│   └───src
│       ├───main
│       │   └───java
│       │       └───com
│       │           └───flexicore
│       │               └───examples
│       │                   ├───data
│       │                   ├───request
│       │                   ├───rest
│       │                   └───service
│       └───test
│           ├───java
│           │   └───com
│           │       └───flexicore
│           │           └───examples
│           │               └───app
│           └───resources
├───person-model
│   └───src
│       └───main
│           ├───java
│           │   └───com
│           │       └───flexicore
│           │           └───example
│           │               └───person
│           └───resources
└───person-service
    └───src
        ├───main
        │   └───java
        │       └───com
        │           └───flexicore
        │               └───examples
        │                   ├───data
        │                   ├───request
        │                   ├───rest
        │                   └───service
        └───test
            ├───java
            │   └───com
            │       └───flexicore
            │           └───examples
            │               └───app
            └───resources
```

We need to build and install (some of the depend-on artifacts) the following projects

```bash
cd person-model
mvn clean install
#built the person-model the person-service requires
cd ../person-service
mvn clean install
# we 'maven' install the person service as it will be needed by the library service.

#in case the folders are not there yet
mkdir /home/flexicore/plugins
mkdir /home/flexicore/entities
# the above assumes using Windows PowerShell
#copy the person-model jar to default location
cp C:\Users\User\source\FlexiCore-Examples\person-model\target\person-model-2.0.0.jar  C:\home\flexicore\entities\

#copy the person-service plugin to default location
 cp C:\Users\User\source\FlexiCore-Examples\person-service\target\person-service-2.0.0.jar C:\home\flexicore\plugins\



#we now have the person service and model in the default location for FlexiCore plugins

```

now stop the running Spring application from the previous stage and run it again, unchanged.

```
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar C:\Users\User\source\flexicore-boot-integration-demo\target\pet-server-1.0.0-exec.jar

#make sure that you corectly change the path to the location of pet-server-1.0.0-exec.jar on your system

```



## Plugins copied to folders,  new APIs appear....

We will now just copy the plugins we have created and see that additional APIs are added after restart (of the Spring-Boot app, **the application is not changed**)

plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.



 ## How to Test?

 Swagger interface can be found in http://localhost:8080/swagger-ui.html , note that in the 2.0.0 fake authentication mechanism is used so a fake token should be provided when using swagger ( if the API requires a token).



```
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar C:\Users\User\source\flexicore-boot-integration-demo\target\pet-server-2.0.0-exec.jar

#replace with the correct location for your Spring Boot application
```

wait till you see the last message indicating that the server has started

```
localhost:8080/swagger-ui.html
```

![second-stage-1.jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/second-stage-1.jpg?raw=true)

**As we can see by copying the plugins to correct folders, additional APIs (See the *Person* API), business workflows and domain model entities are now available on Swagger UI.**

## Adding now additional plugins depending on the *Person * plugin set

```
cd ..\FlexiCore-Examples\library-model\
# make sure you have the right folder
cd ../library-service
mvn clean install  -DskipTests
 cp .\target\library-service-2.0.0.jar C:\home\flexicore\plugins\
 cp ..\library-model\target\library-model-2.0.0.jar C:\home\flexicore\entities\
 #you now should see in the plugins in the respective folder like this
tree C:\home\flexicore\ /f

├───entities
│       library-model-2.0.0.jar
│       person-model-2.0.0.jar
│
└───plugins
        library-service-2.0.0.jar
        person-service-2.0.0.jar
        
#make sure that that you see the above
```

now restart the server

```
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar C:\Users\User\source\flexicore-boot-integration-demo\target\pet-server-2.0.0-exec.jar

#replace with the correct location for your Spring Boot application
```

wait till the server ready and access it:

```
localhost:8080/swagger-ui.html
```

You should now see additional APIs for managing library entities, subscriptions etc.

<img src="https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/stage%204.jpg?raw=true" alt="stage 4.jpg" style="zoom:50%;" />



## Enable tests on library-service

We have built the library-service with the *-DskipTests* option.

this is because there must be a database set for the tests, this database is dropped on every run and it is not the 'pet database' 

**you can skip this part and move to Branch 3.0.0 below**

Setting the database and user for the test

```bash
'C:\Program Files\PostgreSQL\13\scripts\runpsql.bat'
# enter on all prompts
# provide the password for adminstration 
#then
 create database "flexicore-boot";
# may fail if this database exists
 create user "flexicore-boot" with password 'flexicore-boot';
 # may fail if this user exists
 grant all privileges on database "flexicore-boot" to "flexicore-boot";
#note the single and double quotes needed because of the hyphen in the name.
\q
# to exit PSQL
```

now you can run the build of the *library-service*  with tests.

```bash
 cd C:\Users\User\source\FlexiCore-Examples\library-service\
 mvn clean install
```

After a while , you should see the 'build success' message, it will be provided **only** if all tests pass.



# Branch 3.0.0

[FlexiCore Boot Dynamic UI](https://support.wizzdi.com/#dynamic-user-interface) Capabilities Demo.

The goal for this branch is to show how the addition of REST APIs via plugins plus the APIs available in the base *Pet* application plus the APIs available in the framework are all provided in a way suitable for DynamicUI operation.

The current version of this tutorial doesn't include any user interface components, if you are interested, contact us.

Basically, you can see below how the Metadata on the APIs (of all three types ) is created.

## Prerequisites

- The standard Swagger-UI cannot be used, instead download this front end code from [FlexiCore Swagger ui](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/be414b56ca1878d013f900bc67bbb9f4fbac9d73/swagger-ui.zip)

- the zip file should be opened into c:\home\flexicore\ui-swagger

  This location is defined in the application.properties file in resources , here:

  ───src
  │   └───main
  │       ├───java
  │       │   └───com
  │       │       └───example
  │       │           └───pet
  .....
  │       │
  │       └───resources
  │               application.properties

  once the zip file is opened the structure of c:\home\flexicore  is:

  │   firstRun.txt  **this file stores the random password for sign-in**
  │   jwt.secret
  │
  ├───entities
  │       library-model-2.0.0.jar
  │       person-model-2.0.0.jar
  │
  ├───plugins
  │       library-service-2.0.0.jar
  │       person-service-2.0.0.jar
  │
  └───ui-swagger
      │   3rdpartylicenses.txt
      │   background-wizzdi.e6a2a8a32e9402cad5a1.jpeg
    ............

  ​    │
  ​    └───assets

  


## How to build

```bash
cd C:\Users\User\source\flexicore-boot-integration-demo\
git checkout 3.0.0
mvn clean package

```



 ## How to Run ?

run with spring boot properties launcher

    java -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/ -jar pet-server-3.0.0-exec.jar 



 ## How to Test?

This time the server will take sometime to start wait for a lines similar to this:

```bash
zipFileService
zipFileToFileResourceRepository
zipFileToFileResourceService
total of 677 beans
```

(you may see some exceptions in the logs, for example because of a missing MongoDB used for storing health information, simply ignore)





 Swagger interface can be found in http://localhost:8080/FlexiCore/
 login is done using the admin@flexicore.com user this user password is show in a text file called **firstRun.txt** in c:\home\flexicore

the sign-in dialog

![sign-in.jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/sign-in.jpg?raw=true)

After sign-in (Login) wait for a while till Swagger UI is prepared.

then you should see something similar to this

![flexicore-swagger.jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/flexicore-swagger.jpg?raw=true)

you may search for getallinvokers API as follows:

![Dynamic Invokers .jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/Dynamic%20Invokers%20.jpg?raw=true)

Use the 'Try it Out' option to see how your APIs have created ate the server the required data for dynamic access:

![calling getallinvokers.jpg](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/master/calling%20getallinvokers.jpg?raw=true)

See below the created JSON, look for the APIs created at each stage.
[getAllInvokers result](https://github.com/wizzdi/flexicore-boot-integration-demo/blob/1c6616a586cd4cf41f9e1acb9954e369b8c8027e/invokers.json)

Expalaining how to use Invokers and other Classes for dynamic User Interface is out of scope for this tutorial, you can contact us for further help.

