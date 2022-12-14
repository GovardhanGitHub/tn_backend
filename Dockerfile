# for build
FROM gradle AS build
COPY --chown=gradle:gradle . /home/adminroot/tn/tn_backend
WORKDIR /home/adminroot/tn/tn_backend
RUN gradle build --no-daemon
RUN cd /home/adminroot/tn/tn_backend/build/libs/
RUN ls

# for deploy
FROM azul/zulu-openjdk:15
EXPOSE 8082
RUN mkdir /app
COPY --from=build /home/adminroot/tn/tn_backend/build/libs/*SNAPSHOT.jar /app/spring-boot-application.jar
RUN ls /app/
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
