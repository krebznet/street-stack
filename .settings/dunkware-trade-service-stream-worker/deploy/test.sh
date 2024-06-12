
echo "Deploying Worker Service"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server:latest .
docker tag dunkware-trade-service-worker-server:latest testrock1:31000/dunkware-trade-service-worker-server:latest
docker push testrock1:31000/dunkware-trade-service-worker-server:latest
