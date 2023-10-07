FROM openjdk:11

VOLUME /tmp
ADD target/dunkware-trade-service-tick-server-1.2.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx168g -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8987
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
