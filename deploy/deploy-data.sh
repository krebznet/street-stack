echo "Deploying Data Service"
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-street-data/projects/dunkware-trade-service-data-server
docker build --platform=linux/amd64 -t dunkware-trade-service-data-server:latest .
docker tag dunkware-trade-service-data-server:latest testrock1:31000/dunkware-trade-service-data-server:latest
docker push testrock1:31000/dunkware-trade-service-data-server:latest
