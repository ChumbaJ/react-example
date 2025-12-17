pipeline {
    agent any

    stages {
        stage("build application") {
            steps {
                script {
                    echo "building the application..."
                    sh "npm run build"
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: '0b3a3b67-bd80-4d37-ad3a-4b2018b53d7a',
                                  usernameVariable: 'USER',
                                  passwordVariable: 'PASS')]) {
                        sh "docker build -t chumbaj13/myrepo:react-app-2.0 ."
                        sh "echo $PASS | docker login -u ${USER} -p --password-stdin"
                        sh "docker push chumbaj13/myrepo:react-app-2.0"
                    }
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    echo "deploying the application..."
                }
            }
        }
    }
}