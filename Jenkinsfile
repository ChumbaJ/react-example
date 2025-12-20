pipeline {
    agent any

    stages {
        stage("increment version") {
            steps {
                script {
                    echo "incrementing app version..."
                    env.BASE_VERSION = sh(
                        script: "node -p \"require('./package.json').version\"",
                        returnStdout: true
                    ).trim()

                    env.GIT_SHA = sh(
                      script: "git rev-parse --short HEAD",
                      returnStdout: true
                    ).trim()
                }
            }
        }

        stage("build application") {
            steps {
                script {
                    echo "building the application..."
                    sh "npm install"
                    sh "npm run build"
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    env.DEV_TAG = "${env.BASE_VERSION}-dev.${env.GIT_SHA}"

                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: '0b3a3b67-bd80-4d37-ad3a-4b2018b53d7a',
                                  usernameVariable: 'USER',
                                  passwordVariable: 'PASS')]) {
                        sh "docker build -t chumbaj13/myrepo:${env.DEV_TAG} ."
                        sh 'echo "${PASS}" | docker login -u "${USER}" --password-stdin'
                        sh "docker push chumbaj13/myrepo:${env.DEV_TAG}"
                    }
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    echo "deploying the application..."
                    sshagent(['ec2-server']) {

                            // docker rm -f my-app || true
                            // docker run -d -p 80:80 --name my-app --restart=always chumbaj13/myrepo:${DEV_TAG}

                        def DockerCmd = """

                            echo "HELLO FROM JENKINS - ${DEV_TAG}" > jenkins.txt
                        """.trim()

                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.127.40.112 ${DockerCmd}"
                    }
                }
            }
        }
    }
}
