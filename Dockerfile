FROM openjdk:oraclelinux8
WORKDIR /app
COPY . ./
RUN ./mvnw dependency:go-offline
CMD ["./mvnw", "spring-boot:run"]
