#Compile
FROM gradle:8.5-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar -x test --no-daemon


#Run
FROM openjdk:17-jdk-alpine
EXPOSE 8080 5005
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar
RUN echo Build succesful
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/application.jar"]