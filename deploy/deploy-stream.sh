echo "Executing Maven Build"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/

mvn clean install  -DskipTests

echo "Deploying Stream Stats"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-streamstats-server
docker build --platform=linux/amd64 -t dunkware-trade-service-streamstats-server:latest .
docker tag dunkware-trade-service-streamstats-server:latest testrock1:31000/dunkware-trade-service-streamstats-server:latest
docker push testrock1:31000/dunkware-trade-service-streamstats-server:latest
