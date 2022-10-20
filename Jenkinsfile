pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'cd rpg && ./mvnw compile'
            }
        }
        stage('DockerUp') {
            steps {
                echo 'Lancement du Docker'
            }
        }
        stage('Test') {
            steps {
                sh "cd rpg && ./mvnw test"
            }
        }
        stage('DockerDown') {
            steps {
                echo 'Shut down du Docker'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploiement'
            }
        }
    }
}