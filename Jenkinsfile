stage('SonarQube Analysis') {
    steps {
        withSonarQubeEnv('sonarqube') {
            sh '''
            mvn sonar:sonar \
            -Dsonar.projectKey=employee-attendance-tracker \
            -Dsonar.host.url=http://3.108.59.197:9000 \
            -Dsonar.login=$SONAR_AUTH_TOKEN
            '''
        }
    }
}
