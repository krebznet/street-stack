FROM openjdk:11
VOLUME /tmp
ADD target/dunkware-trade-service-data-server-1.0.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx30g -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8086
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
RUN mkdir -p /home/data
RUN mkdir -p /home/data/entity/stats
ADD deployment/logback.xml /home/boot/log/
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
