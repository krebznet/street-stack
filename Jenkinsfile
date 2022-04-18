pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        script {
          // pull from github

          git branch: 'main',
            credentialsId: 'krebznet-github',
            url: 'git@github.com:krebznet/dunkware-street-cloud.git'
        }
        withMaven(globalMavenSettingsConfig: 'dunkware-settings', maven: '3.8.1', options: [pipelineGraphPublisher(includeReleaseVersions: true), pipelineGraphPublisher(lifecycleThreshold: 'install')]) {
          sh 'mvn clean install'
        }

      }

    }

    // docker image deploy stage 
    stage('Deploying Tick Server Image') {
      // Build/Push Tick Server Image 
      steps {
        sh "pwd"
        dir('projects/dunkware-trade-service-tick-server') {
          sh "pwd"
          script {
            docker.withRegistry(
              'https://505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server', 'ecr:us-east-1:ecr-dunkware-internal'
            ) {
              def Image = docker.build('dunkware-trade-service-tick-server')
              Image.push()
            }
          }
        }
      }
    }
      stage('Deploying Web Server Image') {
      // Build/Push Tick Server Image 
      steps {
        sh "pwd"
        dir('projects/dunkware-trade-service-web-server') {
          sh "pwd"
          script {
            docker.withRegistry(
              'https://505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-web-server', 'ecr:us-east-1:ecr-dunkware-internal'
            ) {
              def Image = docker.build('dunkware-trade-service-web-server')
              Image.push()
            }
          }
        }
      }
    }
     stage('Deploying  Data Server Image') {
      // Build/Push Tick Server Image 
      steps {
        sh "pwd"
        dir('projects/dunkware-trade-service-data-server') {
          sh "pwd"
          script {
            docker.withRegistry(
              'https://505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-data-server', 'ecr:us-east-1:ecr-dunkware-internal'
            ) {
              def Image = docker.build('dunkware-trade-service-data-server')
              Image.push()
            }
          }
        }
      }
    }
     stage('Deploying  Stream Server Image') {
      // Build/Push Tick Server Image 
      steps {
        sh "pwd"
        dir('projects/dunkware-trade-service-stream-server') {
          sh "pwd"
          script {
            docker.withRegistry(
              'https://505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-stream-server', 'ecr:us-east-1:ecr-dunkware-internal'
            ) {
              def Image = docker.build('dunkware-trade-service-stream-server')
              Image.push()
            }
          }
        }
      }
    }
  }

}