def buildJar(){
  echo "Building Jar version ${VERSION}"
  sh 'mvn package'
}

def buildImage(){
  echo "Building Image version ${VERSION}"
  withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]){
    sh('docker build -t localhost:8085/spring-boot-zero-hero:${VERSION} .')
    sh('echo $PASS | docker login -u $USERNAME --password-stdin localhost:8085')
    sh('docker push localhost:8085/spring-boot-zero-hero:${VERSION}')
  }
}

def testApp(){
  echo "Testing application"
}

def deployApp(){
  echo "Deploying application version ${params.VERSION}"
  echo "Deploying in ${ONE}"
  echo "Deploying in ${TWO}"
}

return this
