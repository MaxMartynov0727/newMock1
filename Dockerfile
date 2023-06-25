FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /target/newMock-1.0-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]
