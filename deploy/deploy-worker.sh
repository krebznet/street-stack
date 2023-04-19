echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud

mvn clean install  -DskipTests

echo "Deploying Worker Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server .
docker tag dunkware-trade-service-worker-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
