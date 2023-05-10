echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud

mvn clean install  -DskipTests

echo "Deploying Gateway Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-web-server
docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest .
docker tag dunkware-trade-service-web-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-web-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-web-server:latest
