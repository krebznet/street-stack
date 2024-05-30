echo "Deploying Tick Test"

docker build --platform=linux/amd64 -t street-tick:1.0.0-SNAPSHOT .
docker tag street-tick:1.0.0-SNAPSHOT testrock1:31000/street-tick:1.0.0-SNAPSHOT
docker push testrock1:31000/street-tick:1.0.0-SNAPSHOT
