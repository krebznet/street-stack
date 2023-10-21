echo "Deploying Data Test Service"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-service-data-server:latest .
docker tag dunkware-trade-service-data-server:latest testrock1:31000/dunkware-trade-service-data-server:latest
docker push testrock1:31000/dunkware-trade-service-data-server:latest
