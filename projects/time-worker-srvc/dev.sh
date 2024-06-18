
echo "Deploying Worker Service"
docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server:latest .
docker tag dunkware-trade-service-worker-server:latest devrock1:31000/dunkware-trade-service-worker-server:latest
docker push devrock1:31000/dunkware-trade-service-worker-server:latest
