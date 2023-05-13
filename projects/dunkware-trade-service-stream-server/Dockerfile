FROM openjdk:11
VOLUME /tmp
ADD target/dunkware-trade-service-stream-server-1.3.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx28g -Djava.security.egd=file:/dev/./streamservice"
EXPOSE 8086
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
ADD deployment/logback.xml /home/boot/log/
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
