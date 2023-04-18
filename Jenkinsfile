#!/usr/bin/env groovy

//calling shared library in jenkins instead of local groovy file
@Library('jenkins-shared-library')
def gv

pipeline {
  
  agent any
  
  tools {
    maven 'maven-3.9'
  }

  parameters {
    booleanParam(name: 'executeTests', defaultValue: true, description: 'Determine if execute the test')
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
           buildJar()
        }
      }
    }
    
    stage("build image") {
      steps { 
        script {
           buildImage()
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
      steps {
        script {
          gv.deployApp()
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
