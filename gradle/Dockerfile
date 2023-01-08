FROM adoptopenjdk/openjdk11:alpine-jre
ADD /build/libs/*.jar ./app.jar
EXPOSE 8080
CMD java -Xmx2048m -Djava.security.egd=file:/dev/./urandom -jar /app.jar