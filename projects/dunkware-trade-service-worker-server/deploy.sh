docker build --platform=linux/amd64 -t dunkware-trade-service-worker-server:latest .
docker tag dunkware-trade-service-worker-server:latest testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
docker push testrock1.dunkware.net:31000/dunkware-trade-service-worker-server:latest
