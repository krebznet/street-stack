FROM openjdk:17

VOLUME /tmp
ADD target/time-stream-srvc-1.0.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx28g -Djava.security.egd=file:/dev/./streamservice"
EXPOSE 8086
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
