pipeline {
    agent any
    stages {
    
        stage('Build') {
            steps {
                script{
                 // pull from github

                    git branch: 'main',
                    credentialsId: 'krebznet-github',
                    url: 'git@github.com:krebznet/dunkware-street-cloud.git'
            }
                      withMaven(globalMavenSettingsConfig: 'dunkware-settings', maven: '3.8.1', options: [pipelineGraphPublisher(includeReleaseVersions: true),pipelineGraphPublisher(lifecycleThreshold: 'install')]) {
 			sh 'mvn clean install'
}
            
        }
        }
     
        
        // docker image deploy stage 
        stage('Buiding test images') {
           // Build Tick Server Image 
           steps {
            sh '''
            cd $WORKSPACE/projects/dunkware-trade-service-tick-server
            ''' 
            script{
                    docker.withRegistry(
                        'https://505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server', 'ecr:us-east-1:dunkware-ecr-internal'
                        ){
                    def Image = docker.build('dunkware-trade-service-tick-server')
                    Image.push()}
                }
  }
}
    }
}



