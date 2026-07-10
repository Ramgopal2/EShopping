pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out EShopping source code'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java application'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests'
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Creating Docker image'
                sh 'docker build -t eshopping-app .'
            }
        }

        stage('Docker Run') {
            steps {
                echo 'Running application container'
                sh '''
                docker stop eshopping-container || true
                docker rm eshopping-container || true
                docker run -d --name eshopping-container -p 8080:8080 eshopping-app
                '''
            }
        }
    }

    post {
        success {
            echo 'EShopping pipeline completed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}