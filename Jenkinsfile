pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Build started'
                sh 'echo Hello from Jenkins'
                sh 'date'
            }
        }
    }
}