# generate OWASP reports
mvn clean install
# generate OWASP reports
mvn dependency-check:aggregate -PsonarReports
# actual SonarQube analysis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=spring-liberty-producer \
  -Dsonar.host.url=http://localhost:9001 \
  -Dsonar.login=sqp_444f105ab593613986be95f26e143ceb9143e919
  
pause