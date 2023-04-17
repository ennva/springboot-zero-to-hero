def buildApp(){
  echo "Building application version ${NEW_VERSION}"
}

def testApp(){
  echo "Testing application"
}

def deployApp(){
  echo "Deploying application version ${params.VERSION}"
  echo "Deploying in ${ENV}"
}

return this
