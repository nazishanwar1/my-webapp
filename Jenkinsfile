pipeline {
    agent any
    environment {
        APP_NAME = "my-webapp"
    }
    tools {
        maven 'maven-3.9.6'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/nazishanwar1/my-webapp.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Deploy') {
            steps {
                deploy adapters: [
            	    tomcat9(
			credentialsId: 'f5e0bf33-60ac-4ca2-a373-1cc0616a3a6b', 
                        path: '', 
                        url: 'http://192.168.100.103:8080')
        ], contextPath: 'my-webapp', war: 'target/my-webapp.war'}
        }
    }
    post {
        success {
            echo "Deployed! Access: http://<server-ip>:8080/${APP_NAME}"
        }
    }
}

