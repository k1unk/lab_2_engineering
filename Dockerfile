FROM java:8
ADD /out/artifacts/lab_2_jar/lab_2.jar lab_2.jar
ENTRYPOINT ["java","-jar","lab_2.jar"]