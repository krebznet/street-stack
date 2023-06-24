docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest .
docker tag dunkware-trade-service-web-server:latest testrock1:31000/dunkware-trade-service-web-server:latest
docker push testrock1:31000/dunkware-trade-service-web-server:latest
