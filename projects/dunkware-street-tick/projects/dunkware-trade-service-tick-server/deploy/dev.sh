echo "Deploying Tick Dev"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-service-tick-server:latest .
docker tag dunkware-trade-service-tick-server:latest devrock1:31000/dunkware-trade-service-tick-server:latest
docker push devrock1:31000/dunkware-trade-service-tick-server:latest
