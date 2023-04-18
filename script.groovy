def buildJar(){
  when {
    expression {
      BRANCH_NAME == 'master'
    }
  }
  echo "Building Jar version ${NEW_VERSION}"
  sh 'mvn package'
}

def buildImage(){
  when {
    expression {
      BRANCH_NAME == 'master'
    }
  }
  echo "Building Image version ${NEW_VERSION}"
  withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
    sh('docker build -t localhost:8085/spring-boot-zero-hero:${VERSION} .')
    sh('echo $PASS | docker login -u $USERNAME --password-stdin localhost:8085')
    sh('docker push localhost:8085/spring-boot-zero-hero:${VERSION}')
  }
}

def testApp(){
  echo "Testing application"
  echo "Executing pipeline for branch $BRANCH_NAME"
}

def deployApp(){
  when {
    expression {
      BRANCH_NAME == 'master'
    }
  }
  echo "Deploying application version ${params.VERSION}"
  echo "Deploying in ${ONE}"
  echo "Deploying in ${TWO}"
}

return this
