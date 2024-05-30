
echo "Deploying Data Injest Service"

docker build --platform=linux/amd64 -t street-stats:1.0.0-SNAPSHOT .
docker tag street-stats:1.0.0-SNAPSHOT testrock1:31000/street-stats:1.0.0-SNAPSHOT
docker push testrock1:31000/street-stats:1.0.0-SNAPSHOT
