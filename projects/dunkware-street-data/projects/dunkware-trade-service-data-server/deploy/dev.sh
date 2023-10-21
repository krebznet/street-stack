echo "Deploying Data Dev"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-service-data-server:latest .
docker tag dunkware-trade-service-data-server:latest devrock1:31000/dunkware-trade-service-data-server:latest
docker push devrock1:31000/dunkware-trade-service-data-server:latest
