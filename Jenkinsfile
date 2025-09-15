pipeline {
    agent any
    environment {
        APP_NAME = "my-webapp"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/<your-user>/<your-repo>.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                  cp target/${APP_NAME}.war /opt/tomcat/webapps/${APP_NAME}.war
                  /opt/tomcat/bin/shutdown.sh || true
                  /opt/tomcat/bin/startup.sh
                '''
            }
        }
    }
    post {
        success {
            echo "Deployed! Access: http://<server-ip>:8080/${APP_NAME}"
        }
    }
}

