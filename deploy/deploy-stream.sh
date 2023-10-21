
echo "Deploying Stream Stats"
cd /Users/duncankrebs/dunkworld/repos/street-cloud/projects/dunkware-trade-service-stream-server
docker build --platform=linux/amd64 -t dunkware-trade-service-streamtest-server:latest .
docker tag dunkware-trade-service-streamtest-server:latest testrock1:31000/dunkware-trade-service-streamtest-server:latest
docker push testrock1:31000/dunkware-trade-service-streamtest-server:latest
