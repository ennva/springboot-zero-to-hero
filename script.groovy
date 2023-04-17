def buildApp(){
  echo "Building application version ${NEW_VERSION}"
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
