echo "Executing Maven Build"
cd ${STREET_CLOUD_HOME}

#mvn clean install  -DskipTests
#echo "Deploying Cluster Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-net-cluster-server
docker build --platform=linux/amd64 -t dunkware-net-cluster-server:latest .
docker tag dunkware-net-cluster-server:latest testrock1:31000/dunkware-net-cluster-server:latest
docker push testrock1:31000/dunkware-net-cluster-server:latest
echo "Deploying Worker Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-worker-server
docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server:latest .
docker tag dunkware-trade-service-worker-server:latest testrock1:31000/dunkware-trade-service-worker-server:latest
docker push testrock1:31000/dunkware-trade-service-worker-server:latest
echo "Deploying Tick Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-tick-server
docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server:latest .
docker tag dunkware-trade-service-tick-server:latest testrock1:31000/dunkware-trade-service-tick-server:latest
docker push testrock1:31000/dunkware-trade-service-tick-server:latest
echo "Deploying Stream Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-stream-server
docker build --platform=linux/amd64 -t dunkware-trade-service-streamtest-server:latest .
docker tag dunkware-trade-service-streamtest-server:latest testrock1:31000/dunkware-trade-service-streamtest-server:latest
docker push testrock1:31000/dunkware-trade-service-streamtest-server:latest
echo "Deploying Gateway Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-web-server
docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest5 .
docker tag dunkware-trade-service-web-server:latest5 testrock1:31000/dunkware-trade-service-web-server:latest5
docker push testrock1:31000/dunkware-trade-service-web-server:latest5
echo "Deploying Trade Service"
cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-beach-server
docker build --platform=linux/amd64 -t dunkware-trade-service-beach-server:latest5 .
docker tag dunkware-trade-service-beach-server:latest5 testrock1:31000/dunkware-trade-service-beach-server:latest5
docker push testrock1:31000/dunkware-trade-service-beach-server:latest5
