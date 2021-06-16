FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/wizzdi/flexicore-boot-integration-demo.git
WORKDIR /app/flexicore-boot-integration-demo
RUN git checkout --track origin/3.0.0
WORKDIR /app
RUN git clone https://github.com/wizzdi/FlexiCore-Examples.git


FROM maven:3.6.3-openjdk-11 as build
WORKDIR /app
RUN mkdir flexicore-boot-integration-demo
COPY --from=clone /app/flexicore-boot-integration-demo /app/flexicore-boot-integration-demo
RUN mkdir FlexiCore-Examples
COPY --from=clone /app/FlexiCore-Examples /app/FlexiCore-Examples
WORKDIR /app/flexicore-boot-integration-demo
RUN mvn install -DskipTests
WORKDIR /app/FlexiCore-Examples/person-model
RUN mvn install -DskipTests
WORKDIR /app/FlexiCore-Examples/person-service
RUN mvn install -DskipTests
WORKDIR /app/FlexiCore-Examples/library-model
RUN mvn install -DskipTests
WORKDIR /app/FlexiCore-Examples/library-service
RUN mvn install -DskipTests

FROM adoptopenjdk/openjdk11 as run
WORKDIR /app

COPY --from=build /app/flexicore-boot-integration-demo/target/pet-server-*-exec.jar /app/pet-server.jar
RUN mkdir -p /home/flexicore/plugins
RUN mkdir -p /home/flexicore/entities

COPY --from=build /app/FlexiCore-Examples/person-model/target/person-model-*.jar /home/flexicore/entities/person-model.jar
COPY --from=build /app/FlexiCore-Examples/person-service/target/person-service-*.jar /home/flexicore/plugins/person-service.jar
COPY --from=build /app/FlexiCore-Examples/library-model/target/library-model-*.jar /home/flexicore/entities/library-model.jar
COPY --from=build /app/FlexiCore-Examples/library-service/target/library-service-*.jar /home/flexicore/plugins/library-service.jar

RUN apt-get update && apt-get -qq -y install wget libcurl4 openssl liblzma5  gnupg maven lsb-release

ENV TZ=Israel
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get clean &&apt-get update && apt-get -qq -y install postgresql postgresql-contrib vim net-tools psmisc
# Run the rest of the commands as the ``postgres`` user created by the ``postgres-9.3`` package when it was ``apt-get installed``

USER postgres

# Create a PostgreSQL role named ``docker`` with ``docker`` as the password and
# then create a database `docker` owned by the ``docker`` role.
# Note: here we use ``&&\`` to run commands one after the other - the ``\``
#       allows the RUN command to span multiple lines.
RUN    /etc/init.d/postgresql start &&\
    psql --command "CREATE USER pet WITH SUPERUSER PASSWORD 'pet';" &&\
    createdb -O pet pet

# Adjust PostgreSQL configuration so that remote connections to the
# daitabase are possible.
RUN cd /etc/postgresql/*/main/&&echo "host all  all    0.0.0.0/0  md5" >>pg_hba.conf && echo "listen_addresses='*'" >>postgresql.conf

# Expose the PostgreSQL port
EXPOSE 5432

# Add VOLUMEs to allow backup of config, logs and databases
VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

EXPOSE 8080
EXPOSE 8787
USER root
RUN mkdir -p /data/db


CMD ["/bin/bash","-c","/usr/sbin/service postgresql start&&java -jar /app/pet-server.jar"]

