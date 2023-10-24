echo "Deploying Tick Test"

docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server:latest .
docker tag dunkware-trade-service-tick-server:latest testrock1:31000/dunkware-trade-service-tick-server:latest
docker push testrock1:31000/dunkware-trade-service-tick-server:latest
