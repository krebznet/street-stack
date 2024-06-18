
echo "Deploying Worker Service"

docker build --platform=linux/amd64 -t street-worker:1.0.0-SNAPSHOT .
docker tag street-worker:1.0.0-SNAPSHOT testrock1:31000/street-worker:1.0.0-SNAPSHOT
docker push testrock1:31000/street-worker:1.0.0-SNAPSHOT
