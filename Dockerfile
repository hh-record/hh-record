FROM adoptopenjdk/openjdk11
ENV APP_HOME=/usr/app
ENV JAR_FILE=backend-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY ./ ./
RUN chmod +x gradlew
RUN ./gradlew clean bootJar
ENTRYPOINT ["java", "-jar", "/usr/app/build/libs/backend-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]

#FROM adoptopenjdk/openjdk11 AS BUILD
#ENV APP_HOME=/usr/app
#WORKDIR $APP_HOME
#COPY ./ ./
#RUN chmod +x gradlew
#RUN ./gradlew clean bootJar
#
#FROM adoptopenjdk/openjdk11
#ENV APP_HOME=/usr/app
#ENV JAR_FILE=backend-0.0.1-SNAPSHOT.jar
#COPY --from=BUILD  $APP_HOME/build/libs/$JAR_FILE $APP_HOME/backend.jar
#EXPOSE 9999
#ENTRYPOINT ["java", "-jar", "/usr/app/backend.jar", "--spring.profiles.active=dev"]