echo "Deploying Stream Service"

docker build --platform=linux/amd64 -t dunkware-trade-service-streamstats-server:latest .
docker tag dunkware-trade-service-streamtest-server:latest testrock1:31000/dunkware-trade-service-streamstats-server:latest
docker push testrock1:31000/dunkware-trade-service-streamstats-server:latest
