def gv

pipeline {
  
  agent any
  
  tools {
    maven 'maven-3.9'
  }
  
  parameters {
    string(name: 'VERSION', defaultValue: '0.0.1', description: 'version to deploy on PROD')
    //choice(name: 'VERSION', choices: ['0.0.1','0.0.2','0.0.3'], description: 'version to deploy on PROD')
    booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }
  
  environment {
    NEW_VERSION = '0.0.1'
    SERVER_CREDENTIALS = credentials('server-credentials')
  }
  
  stages {
    stage("init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }
    
    stage("build jar") {
      steps { 
        script {
           gv.buildJar()
        }
      }
    }
    
    stage("build image") {
      steps { 
        script {
           gv.buildImage()
        }
      }
    }
    
    stage("test") {
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
    
    stage("deploy") {
      input {
        message "Select the environment to deploy to"
        ok "Done"
        parameters {
          choice(name: 'ONE', choices: ['dev','staging','prod'], description: 'env to deploy to')
          choice(name: 'TWO', choices: ['dev','staging','prod'], description: 'env to deploy to')
        }
      }
      steps {
        script {
          gv.deployApp()
        }
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
