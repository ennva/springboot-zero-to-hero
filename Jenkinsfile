pipeline {
    agent any

    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'wether to execute tests')
    }

    stages {
        stage("test"){
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    echo "Testing application ..."
                }
            }
        }

        stage("build Jar"){
            steps {
                script {
                    echo "Building jar package.."
                }
            }
        }

        stage("build Image"){
            steps {
                script {
                    echo "Building docker image"
                }
            }
        }

        stage("deploy"){
            steps {
                script {
                    echo "Deploying docker image to ec2.."
                    def dockerCmd = 'docker run -d -p 8080:8080 djass/spring-boot-zero-hero:0.0.2'
                    sshagent(['ec2-server-key-2']) {
                        sh("ssh -o StrictHostKeyChecking=no ec2-user@3.75.225.173 ${dockerCmd}")
                    }
                }
            }
        }

    }
}