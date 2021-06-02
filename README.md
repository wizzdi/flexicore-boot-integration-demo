# FlexiCore Boot Integration Demo

This Repository demonstrates how to add FlexiCore Boot capabilities to an existing Spring Boot project.
The code has few branches, each presents additional capabilities, starting with a basic Spring application.

The basic application has no FlexiCore dependencies and is changed to include these capabilities, from that point on, the basic application is expanded via plugins, this include domain model changes, APIs and finally FlexiCore dynamic User interface support.

**Note**: the instructions here have been verified on clean Windows 10 virtual machine, step by step, however, if anything goes wrong, contact info@wizzdi.com for support.

This example uses a database and is configured to use PostgreSQL database.

## Three different options for running this demo

- clone this repository and later in the demo the plugins repositories, this is the recommended way but it requires a manual installation of 

- use a pre-installed Docker from here

- use an installation file for Windows (here) and for Linux (here)



# prerequisites for Windows 

- Java 11 JDK  , can use newer versions,  OpenJDK can be obtained from https://jdk.java.net/archive/

  - If you have another JDK installed, it can be used if >= version 11. However this demo was tested with version 11.

  - The installation in Windows requires adding an environment variable JAVA_HOME and the bin folder of the JDK to Windows path.
  - you can use the tutorial: https://knowledge.exlibrisgroup.com/Aleph/Knowledge_Articles/How_to_Download_and_Install_OpenJDK_11_on_Windows_10_PC_for_Aleph
  - test Java installation by typing (at the command line) *java -version*

- Apache Maven from here https://maven.apache.org/download.cgi

  - Maven is used here for building the artifacts, these can be built using an IDE like IntelliJ/Netbeans/Eclipse.
  - Maven bin folder should be added to the path variable.
  - check by *mvn -version*  

- Install *git *  for Windows from here: https://git-scm.com/download/win

- PostgreSQL database server from here: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

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

FlexiCore Boot Plugin Loading capabilities added , plugins were tested with the Person Service/Model and Library Service/Model found here: https://github.com/wizzdi/FlexiCore-Examples

We will first test just the *Person* model and service. 

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
#build the person-model the person-service requires
cd ../person-service
mvn clean install
# we 'maven' install the person service as it will be needed by the library service.

```

we now need to checkout the Spring app to version 2.00 so it will include the required FlexiCore dependencies.

## FlexiCore boot modules added, no plugins yet..

Our intention is to see how the Spring Boot app looks like with FlexiCore support but with no plugins in designated folders.

**Note that we do not add any dependencies on Person service or model**

We need to use the 2.0.0 branch to add FlexiCore capabilities

```bash
cd ~/source/flexicore-boot-integration-demo
git checkout 2.0.0
mvn clean package
```

make sure build is successful and proceed to testing the system.

 ## How to Run ?

run with spring boot properties launcher

```bash
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar pet-server-2.0.0-exec.jar
#the '' are required when using PowerShell on Windows
```

    wait till the server starts and access the API via Swagger:

usually the last line in the output when server is ready is (number of beans may differ) : **total of 319 beans**

 Below is a typical OpenAPI-Definition with FlexiCore-boot dependencies added to the project *pom.xml* this is the only difference between 1.0.0 and 2.0.0 branches.

https://1drv.ms/u/s!AqyJyQGh1gG8h8wsmPp2tUYhNWnY9w?e=9puovO



## Plugins copied to folders,  new APIs appear....

We will now just copy the plugins we have created and see that additional APIs are added after restart (of the Spring-Boot app, **the application is not changed**)

plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.

```
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





 ## How to Test?

 Swagger interface can be found in http://localhost:8080/swagger-ui.html , note that in the 2.0.0 fake authentication mechanism is used so a fake token should be provided when using swagger ( if the API requires a token).



```
java '-Dloader.main=com.example.pet.Application' '-Dloader.debug=true' '-Dloader.path=file:/home/flexicore/entities/' -jar C:\Users\User\source\flexicore-boot-integration-demo\target\pet-server-2.0.0-exec.jar

#replace with the correct location for your Spring Boot application
```

wait till you see the last message indicating that the server has started

![image-20210602154202962](C:\Users\Avishay Ben Natan\AppData\Roaming\Typora\typora-user-images\image-20210602154202962.png)

**As we can see by copying the plugins to correct folders, additional APIs (See the *Person* API), business workflows and domain model entities are now available on Swagger UI.**



# Branch 3.0.0

[FlexiCore Boot Dynamic UI](https://support.wizzdi.com/#dynamic-user-interface) Capabilities Demo

 ## How to Run ?

run with spring boot properties launcher

    java -Dloader.main=com.example.pet.Application -Dloader.debug=true -Dloader.path=file:/home/flexicore/entities/ -jar pet-server-3.0.0-exec.jar 

plugins should be placed in the /home/flexicore/plugins directory , entities should be placed in the /home/flexicore/entities/ directory.

 ## How to Test?

 Swagger interface can be found in http://localhost:8080/FlexiCore/
 login is done using the admin@flexicore.com user this user password is show in a text file called firstRun.txt in /home/flexicore/
