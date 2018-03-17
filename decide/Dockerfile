FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/decide-0.1.0.jar app.jar
VOLUME ["/application.yml"]
EXPOSE 22443 33443
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar