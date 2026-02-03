# ändra till din version av java om du använder nyare
FROM eclipse-temurin:17-jre
WORKDIR /app
# NOT: ändra target/app.jar till target/<namnet_på_din_jar_fil>
COPY target/HTTP-Docker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]