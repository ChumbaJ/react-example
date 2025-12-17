def buildApp() {
    echo 'building the application'
}

def testApp() {
    echo 'Testing the applicatoin'
}

def deploy() {
    echo "Deploying the applicatoin ${params.Version}"
}

return this