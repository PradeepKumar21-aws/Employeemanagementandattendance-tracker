pipeline {
    agent any


    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/PradeepKumar21-aws/Employeemanagementandattendance-tracker.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=employee-attendance-tracker
                    '''
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t employee-attendance-tracker .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhubcred',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    docker login -u $DOCKER_USER -p $DOCKER_PASS
                    docker tag employee-attendance-tracker pradeep211031/employee-attendance-tracker:latest
                    docker push pradeep211031/employee-attendance-tracker:latest
                    '''
                }
            }
        }
    }
}
