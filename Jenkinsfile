pipeline {    
    agent any

    parameters {
        choice(name: 'Version', choices: ['1.1.0', '1.2.0', '1.3.0'])
        booleanParam(name: 'executeTests', defaultValue: true)
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the applicatoin'
            }
        }

        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }

            steps {
                echo 'Testing the applicatoin'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the applicatoin'
                
            }
        }
    }
}
