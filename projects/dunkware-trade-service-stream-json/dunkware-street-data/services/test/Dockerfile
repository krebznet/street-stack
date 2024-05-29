FROM openjdk:17

VOLUME /tmp
ADD target/data-srvc-test-1.0.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx28g -Djava.security.egd=file:/dev/./streamworker"
EXPOSE 8021
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
