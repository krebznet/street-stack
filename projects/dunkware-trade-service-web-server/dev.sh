
echo "Deploying Stream Dev"
docker build --platform=linux/amd64 -t dunkware-trade-service-web-server:latest5 .
docker tag dunkware-trade-service-web-server:latest5 devrock1:31000/dunkware-trade-service-web-server:latest5
docker push devrock1:31000/dunkware-trade-service-web-server:latest5
