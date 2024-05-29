
echo "Deploying Data Injest Service"

docker build --platform=linux/amd64 -t dunkware-stream-data-test:1.0.0-SNAPSHOT .
docker tag dunkware-stream-data-test:1.0.0-SNAPSHOT testrock1:31000/dunkware-stream-data-test:1.0.0-SNAPSHOT
docker push testrock1:31000/dunkware-stream-data-test:1.0.0-SNAPSHOT
