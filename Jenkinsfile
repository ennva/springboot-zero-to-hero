pipeline {
  
  agent any
  
  parameters {
    //string(name: 'VERSION', defaultValue: '0.0.1', description: 'version to deploy on PROD')
    choice(name: 'VERSION', choices: ['0.0.1','0.0.2','0.0.3'], description: 'version to deploy on PROD')
    booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }
  
  environment {
    NEW_VERSION = '0.0.1'
    SERVER_CREDENTIALS = credentials('server-credentials')
  }
  
  stages {
    when {
      expression {
        params.executeTests
      }
    }
    stage("build") { 
      steps { 
         echo "building the app"
         echo "bilding version ${NEW_VERSION}"
      }
    }
    
    stage("test") {
      steps {
        echo "testing the app"
      }
    }
    
    stage("deploy") {
      steps {
        echo "deploying the application with version ${VERSION}"
        withCredentials([
          usernamePassword('server-credentials', usernameVariable: USER, passwordVariable: PWD)
        ]){
          sh "echo ${uSER},${PWD}"
        }
      }
    }
    
  }
  
  post {
    always {
      // always executed
    }
    success {
      // run if success
    }
    failure {
      // run if failure
    }
  }
}
