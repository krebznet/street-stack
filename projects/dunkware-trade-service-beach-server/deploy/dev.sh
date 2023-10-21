echo "Deploying Tick Dev"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-beach-server-server:latest .
docker tag dunkware-trade-service-beach-server:latest devrock1:31000/dunkware-trade-service-beach-server:latest
docker push devrock1:31000/dunkware-trade-service-beach-server:latest
