FROM eclipse-temurin
WORKDIR /EcoAqua
COPY ./target/classes/ /EcoAqua
ENTRYPOINT ["java", "com.example.EcoAqua.Mongodb.HelloMongoDB"]