# for build
FROM gradle AS build
COPY --chown=gradle:gradle . /home/adminroot/tn/tn_backend WORKDIR /home/adminroot/tn/tn_backend
RUN gradle build --no-daemon
RUN cd /home/adminroot/tn/tn_backend/build/libs/
RUN ls

# for deploy
FROM azul/zulu-openjdk:15
EXPOSE 8081
RUN mkdir /app
COPY --from=build /home/adminroot/tn/tn_backend/build/libs/*SNAPSHOT.jar /app/spring-boot-application.jar
RUN ls /app/
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
