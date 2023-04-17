def gv

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
    statge("init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }
    
    stage("build") {
      steps { 
         echo "building the app"
         echo "bilding version ${NEW_VERSION}"
      }
    }
    
    stage("test") {
       when {
        expression {
          params.executeTests
        }
      }
      steps {
        echo "testing the app"
      }
    }
    
    stage("deploy") {
      steps {
        echo "deploying the application with version ${params.VERSION}"
        withCredentials([
          usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')
        ]){
          sh 'echo ${USER},${PWD}'
        }
      }
    }
    
  }
  
  post {
    always {
      echo "All steps passed"
    }
    success {
      echo "This deployment ended successfully"
    }
    failure {
      echo "An error occur during the deployment"
    }
  }
}
