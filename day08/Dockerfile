FROM openjdk:17-jdk-slim
WORKDIR /app
COPY App.java .
COPY input.txt .
RUN javac App.java
CMD java App