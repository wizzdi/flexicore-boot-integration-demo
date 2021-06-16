FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/wizzdi/flexicore-boot-integration-demo.git
WORKDIR /app/flexicore-boot-integration-demo
RUN git checkout --track origin/2.0.0

FROM alpine/git as clone-plugins
WORKDIR /app
RUN git clone https://github.com/wizzdi/FlexiCore-Examples.git

FROM maven:3.6.3-openjdk-11 as build
WORKDIR /app
COPY --from=clone /app/FlexiCore /app
RUN mvn install -DskipTests

FROM maven:3.6.3-openjdk-11 as build-person-model
WORKDIR /app
COPY --from=clone-plugins /app/FlexiCore-Examples/person-model /app
RUN mvn install -DskipTests

FROM maven:3.6.3-openjdk-11 as build-person-service
WORKDIR /app
COPY --from=clone-plugins /app/FlexiCore-Examples/person-service /app
RUN mvn install -DskipTests

FROM maven:3.6.3-openjdk-11 as build-library-model
WORKDIR /app
COPY --from=clone-plugins /app/FlexiCore-Examples/library-model /app
RUN mvn install -DskipTests

FROM maven:3.6.3-openjdk-11 as build-library-service
WORKDIR /app
COPY --from=clone-plugins /app/FlexiCore-Examples/library-service /app
RUN mvn install -DskipTests

FROM adoptopenjdk/openjdk11 as run
WORKDIR /app

COPY --from=build /app/target/pet-server-*-exec.jar /app/pet-server.jar
RUN mkdir -p /home/flexicore/plugins
RUN mkdir -p /home/flexicore/entities
COPY --from=build-person-model /app/target/person-model-*.jar /home/flexicore/entities/person-model.jar
COPY --from=build-person-service /app/target/person-service-*.jar /home/flexicore/plugins/person-service.jar
COPY --from=build-library-model /app/target/library-model-*.jar /home/flexicore/entities/library-model.jar
COPY --from=build-library-service /app/target/library-service-*.jar /home/flexicore/plugins/library-service.jar

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

