pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk   'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
