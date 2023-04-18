#!/usr/bin/env groovy

//project scope library: declared in jenkins. format library@version/tag
library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [ $class: 'GitSCMSource',
     remote: 'https://github.com/ennva/jenkins-shared-library.git',
     credentialsId: '12631522-0ca8-4486-9f59-0c60b3baecdd'
    ]
)

//Grobal scope shared library in jenkins instead of local groovy file
// create it in global system configuration of jenkins:  Global Pipeline Libraries
// @Library('jenkins-shared-library@master')
//@Library('jenkins-shared-library')


def gv
def host = 'localhost:8085'
def imageName = 'spring-boot-zero-hero'

pipeline {
  
  agent any
  
  tools {
    maven 'maven-3.9'
  }

  parameters {
    booleanParam(name: 'executeTests', defaultValue: true, description: 'Determine if execute the test')
    string(name: 'VERSION', defaultValue: '0.0.1', description: 'version to deploy')
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
           buildImage(host, imageName)
           dockerLogin(host)
           dockerPush(host, imageName)
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
