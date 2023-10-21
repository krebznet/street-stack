
echo "Deploying Stream Dev"
cd ..
docker build --platform=linux/amd64 -t dunkware-trade-service-streamtest-server:latest .
docker tag dunkware-trade-service-streamtest-server:latest devrock1:31000/dunkware-trade-service-streamtest-server:latest
docker push devrock1:31000/dunkware-trade-service-streamtest-server:latest
