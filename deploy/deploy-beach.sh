echo "Executing Maven Build"
cd $STREET_CLOUD_HOME

mvn clean install  -DskipTests

echo "Deploying Beach  Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-beach-server
docker build --platform=linux/amd64 -t dunkware-trade-service-beach-server:latest .
docker tag dunkware-trade-service-beach-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-beach-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-beach-server:latest
