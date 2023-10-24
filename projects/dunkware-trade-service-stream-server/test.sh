
echo "Deploying Stream Test"
docker build --platform=linux/amd64 -t dunkware-trade-service-streamtest-server:testrock .
docker tag dunkware-trade-service-streamtest-server:testrock testrock1:31000/dunkware-trade-service-streamtest-server:testrock
docker push testrock1:31000/dunkware-trade-service-streamtest-server:testrock
