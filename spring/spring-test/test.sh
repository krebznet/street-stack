
echo "Deploying Stream Dev"
docker build --platform=linux/amd64 -t street-test:1.0.0-SNAPSHOT .
docker tag street-test:1.0.0-SNAPSHOT testrock1:31000/street-test:1.0.0-SNAPSHOT
docker push testrock1:31000/street-test:1.0.0-SNAPSHOT
