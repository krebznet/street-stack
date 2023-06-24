docker build --platform=linux/amd64 -t dunkware-trade-service-beach-server:latest .
docker tag dunkware-trade-service-beach-server:latest testrock1:31000/dunkware-trade-service-beach-server:latest
docker push testrock1:31000/dunkware-trade-service-beach-server:latest

