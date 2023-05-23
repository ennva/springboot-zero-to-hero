pipeline {
    agent any

    stages {
        stage("build app"){
            steps {
                script {
                    echo "Building the application ..."
                }
            }
        }

        stage("build image"){
            steps {
                script {
                    echo "Building the docker image..."
                }
            }
        }

        stage("deploy"){
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
            }
            steps {
                script {
                    echo "checking my aws users..."
                    sh("aws iam list-users --no-cli-pager")
                    echo "list available clusters..."
                    sh("aws eks list-clusters --no-cli-pager")
                    //echo "deploying docker image"
                    //sh("kubectl create deployment nginx-deployment --image=nginx")
                }
            }
        }

    }
}
