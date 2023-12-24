
echo "Deploying Stream Dev"
docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest7 .
docker tag dunkware-trade-service-web-server:latest7 testrock1:31000/dunkware-trade-service-web-server:latest7
docker push testrock1:31000/dunkware-trade-service-web-server:latest7
