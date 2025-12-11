pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                // Récupère le code depuis le dépôt configuré dans le job
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build Maven sans exécuter les tests (évite l'erreur MySQL)
                sh 'mvn clean package -DskipTests=true'
            }
        }
    }
}
