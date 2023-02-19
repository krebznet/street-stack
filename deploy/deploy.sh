echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud
echo pwd
  aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 505030817635.dkr.ecr.us-east-1.amazonaws.com
sleep 1
mvn clean install

for user in "$@"
do
  if [ $user = "c" ]
  then
    echo "Deploying Cluster Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
    docker tag dunkware-net-cluster-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-net-cluster-server:latest
    docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-net-cluster-server:latest
  fi
  if [ $user = "t" ]
  then
    echo "Deploying Tick Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
    docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server .
    docker tag dunkware-trade-service-tick-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server:latest
    docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server:latest
    sleep 1
  fi
  if [ $user = "w" ]
    then
    echo "Deploying Worker Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
    docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server .
    docker tag dunkware-trade-service-worker-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-worker-server:latest
    docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-worker-server:latest
    echo "Deploying Gateway Service"
  fi
  if [ $user = "s" ]
    then
    echo "Deploying Stream Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-server

    docker build --platform=linux/amd64 -t dunkware-trade-service-stream-server .
    docker tag dunkware-trade-service-stream-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-stream-server:latest
    docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-stream-server:latest
    sleep 1
  fi
  if [ $user = "g" ]
  then
    echo "Deploying Gateway Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-web-server
    sleep 1
    docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/gatewayservice:latest .
  fi
    i=$((i + 1));
    if [ $user = "all" ]
    then
      echo "Deploying Tick Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
      docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server .
      docker tag dunkware-trade-service-tick-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server:latest
      docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-tick-server:latest
      sleep 1
      echo "Deploying Worker Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
      docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server .
      docker tag dunkware-trade-service-worker-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-worker-server:latest
      docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-worker-server:latest
      echo "Deploying Gateway Service"

      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-web-server
      docker build --platform=linux/amd64 -t dunkware-trade-service-web-server .
      docker tag dunkware-trade-service-web-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-web-server:latest
      docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-web-server:latest
      sleep 1
      echo "Deploying Stream Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-server

      docker build --platform=linux/amd64 -t dunkware-trade-service-stream-server .
      docker tag dunkware-trade-service-stream-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-stream-server:latest
      docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-trade-service-stream-server:latest
      sleep 1


      echo "Deploying Cluster Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
      docker tag dunkware-net-cluster-server:latest 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-net-cluster-server:latest
      docker push 505030817635.dkr.ecr.us-east-1.amazonaws.com/dunkware-net-cluster-server:latest
    fi
      i=$((i + 1));
done

echo "Reploying TestRock Cluster"
ssh mradmin@testrock1.dunkware.net << EOF
   cd /home/mradmin/k8s/street/cloud/cluster
   ./destroy.sh
   sleep 10
   ./deploy.sh
EOF
