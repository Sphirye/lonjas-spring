FROM gradle:jdk11-alpine as builder

USER gradle

ARG BUILD_PATH=/source
ARG GRADLE_HOME=/home/gradle

COPY --chown=gradle:gradle ./ $BUILD_PATH

WORKDIR $BUILD_PATH
RUN --mount=type=cache,target=${GRADLE_HOME}/.gradle gradle --build-cache --no-daemon :bootJar

ENV SERVICE_PORT=8080

EXPOSE $SERVICE_PORT

COPY --from=builder /source/build/libs/*.jar /app.jar
CMD java -Xmx2048m -Djava.security.egd=file:/dev/urandom -jar /app.jar