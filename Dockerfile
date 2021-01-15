FROM java:8
ADD build/libs/*.jar retaildockerapp.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar","retaildockerapp.jar"]

###build image
# docker build -t retailappimage .
### run image as container
# docker run -p  8888:8888 retailappimage