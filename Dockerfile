FROM amazoncorretto:17
ENV TZ=America/Mexico_City
VOLUME /tmp
EXPOSE 8080
ADD build/libs/ms-examenes-0.0.1-SNAPSHOT.jar ms-examenes-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ms-examenes-0.0.1-SNAPSHOT.jar"]