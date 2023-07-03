docker build --platform=linux/amd64 -t dunkware-trade-service-beach-server:latest5 .
docker tag dunkware-trade-service-beach-server:latest5 testrock1:31000/dunkware-trade-service-beach-server:latest5
docker push testrock1:31000/dunkware-trade-service-beach-server:latest5

