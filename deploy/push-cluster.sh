#cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud
#mvn clean install  -DskipTests
echo "Building/Pushing Cluster Image"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
docker build --platform=linux/amd64 .
docker tag dunkware-net-cluster-server:latest testrock1.dunkware.net:31000/dunkware-net-cluster-server:latest
docker push testrock1.dunkware.net:31000/dunkware-net-cluster-server:latest
echo "Building/Pushing Tick Image"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
docker build --platform=linux/amd64 .
docker tag dunkware-trade-service-tick-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-tick-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-tick-server:latest
