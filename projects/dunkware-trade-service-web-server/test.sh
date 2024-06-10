
echo "Deploying Stream Dev"
docker build --platform=linux/amd64 -t street-gateway:2.0.0-SNAPSHOT .
docker tag street-gateway:2.0.0-SNAPSHOT testrock1:31000/street-gateway:2.0.0-SNAPSHOT
docker push testrock1:31000/street-gateway:2.0.0-SNAPSHOT
