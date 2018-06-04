pipeline {
agent any
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
            post {
                always {
                  junit 'target/surefire-reports/*.xml'
                }
            }
	}
        stage('Deliver') { 
            steps {
                sh 'scp target/documentation.war /home/mehdi/dev/projects/Docs/apache-tomcat-7.0.70/webapps' 
            }        
        }
    }
}
