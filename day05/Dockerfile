FROM openjdk:8-alpine
WORKDIR /app
COPY App.java .
COPY input.txt .
RUN javac App.java
CMD java App