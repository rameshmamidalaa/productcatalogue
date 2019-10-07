FROM adoptopenjdk/openjdk8:latest

MAINTAINER Ramesh Mamidala <ramesh.mamidalaa@gmail.com>

COPY /target/productcatalogue-0.0.1-SNAPSHOT.jar /tmp

WORKDIR /tmp

RUN sh -c 'touch productcatalogue-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "productcatalogue-0.0.1-SNAPSHOT.jar"]