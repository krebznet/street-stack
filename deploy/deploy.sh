echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud

mvn clean install  -DskipTests

echo "Deploying Cluster Service"
cd /Users/duncankrebs/dunkware/sreet/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
docker build --platform=linux/amd64 -t dunkware-net-cluster-server .
docker tag dunkware-net-cluster-server:latest testrock1.dunkware.net:31000/dunkware-net-cluster-server:latest
docker push testrock1.dunkware.net:31000/dunkware-net-cluster-server:latest
echo "Deploying Worker Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server .
docker tag dunkware-trade-service-worker-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
echo "Deploying Tick Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server:latest .
docker tag dunkware-trade-service-tick-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-tick-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-tick-server:latest
echo "Deploying Stream Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-server
docker build --platform=linux/amd64 -t dunkware-trade-service-streamtest-server:latest .
docker tag dunkware-trade-service-streamtest-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-streamtest-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-streamtest-server:latest
echo "Deploying Gateway Service"
mvn clean install  -DskipTests
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-web-server
docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest .
docker tag dunkware-trade-service-web-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-web-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-web-server:latest

#echo "Reploying TestRock Cluster"
#ssh mradmin@testrock1.dunkware.net << EOF
#   cd /home/mradmin/k8s/street/cloud/cluster
#   ./destroy.sh
#   sleep 10
#   ./deploy.sh
#EOF
