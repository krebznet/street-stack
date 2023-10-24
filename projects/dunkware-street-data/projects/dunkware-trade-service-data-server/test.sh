echo "Deploying Data Test Service"

docker build --platform=linux/amd64 -t dunkware-trade-service-data-server:testrock .
docker tag dunkware-trade-service-data-server:testrock testrock1:31000/dunkware-trade-service-data-server:testrock
docker push testrock1:31000/dunkware-trade-service-data-server:testrock
