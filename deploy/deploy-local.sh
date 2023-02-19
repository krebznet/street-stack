echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud
echo pwd
sleep 1
mvn clean install

for user in "$@"
do
  if [ $user = "c" ]
  then
    echo "Deploying Cluster Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
    sleep 1
    docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/clusterservice:latest .
  fi
  if [ $user = "t" ]
  then
    echo "Deploying Tick Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
    sleep 1
    docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .
  fi
  if [ $user = "w" ]
    then
    echo "Deploying Worker Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
    sleep 1
    docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .
  fi
  if [ $user = "s" ]
    then
    echo "Deploying Stream Service"
    cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
    sleep 1
    docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/streamservice:latest .
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
      sleep 1
      docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .

      echo "Deploying Worker Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
      sleep 1
      docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .

      echo "Deploying Gateway Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-web-server
      sleep 1
      docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/gatewayservice:latest .
      echo "Deploying Stream Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
      sleep 1
      docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .

      echo "Deploying Cluster Service"
      cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
      sleep 1
      docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/clusterservice:latest .
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
