def gv

pipeline {    
    agent any

    parameters {
        choice(name: 'Version', choices: ['1.1.0', '1.2.0', '1.3.0'])
        booleanParam(name: 'executeTests', defaultValue: true)
    }

    stages {
        stage('Init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }

        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }

            steps {
                script {
                    gv.testApp()
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    env.ENV = input messasge: "Select the env to deploy to", ok "Done", parameters: [choice(name: 'ONE', choices: ['dev', 'staging', 'prod'])]

                    gv.deploy()
                    echo "deploying as ${ENV}"
                }
            }
        }
    }
}
