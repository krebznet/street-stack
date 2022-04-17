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
 			sh 'mvn clean deploy'
}
            
        }}

     
        }
    }



