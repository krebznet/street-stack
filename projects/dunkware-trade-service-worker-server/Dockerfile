FROM openjdk:11
VOLUME /tmp
ADD target/dunkware-trade-service-data-mock-1.0.0-SNAPSHOT.jar application.jar
ENV JAVA_OPTS="-Xmx16g -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8078
RUN mkdir -p /home/boot/config
RUN mkdir -p /home/boot/log
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /application.jar" ]
