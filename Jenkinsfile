pipeline {
    agent none

    stages {
        stage("Fix permissions") {
            agent any

            steps {
                sh "sudo chown root:jenkins /run/docker.sock"
            }
        }
        stage('Build') {
            steps {
                sh 'cd rpg && ./mvnw compile'
            }
        }
        stage('DockerUp') {
            steps {
                sh 'docker-compose -f docker-compose.yml up --build'
            }
        }
        stage('Test') {
            steps {
                sh "cd rpg && ./mvnw test"
            }
        }
        // stage('DockerDown') {
        //     steps {
        //         sh 'docker-compose -f docker-compose.yml down'
        //     }
        // }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}