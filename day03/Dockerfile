FROM gradle:7-jdk8 as BASE
WORKDIR /src
COPY ./ .
RUN gradle build

FROM openjdk:8-alpine
COPY --from=BASE /src/build/libs/day03.jar /app.jar
COPY input.txt .
CMD java -jar /app.jar