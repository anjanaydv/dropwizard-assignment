# dropwizard-assignment

# Intro:
This is a dropwizard maven based restful application. It exposes a rest api which can be used to fetch ip address details like http://localhost:8080/ip-details?ip=1.2.7.3. It uses embedded tomcat web server which runs on default port 8080. It uses cache to cache the result. If result is not found in cache then try to retrieve from in memory db (h2). If found neither of the places then try to retrieve from http://ip-api.com/json/. 

# Setup:
Since this is not Spring project. We have to run manually like:```java -jar dropwizard-assignment2-1.0-SNAPSHOT.jar server ../config/config.yaml``` it requires latest version of java and maven ```3.6.1```. See the ```pom.xml``` and other configuration for details.

TODO:
Add test cases and improve more 
