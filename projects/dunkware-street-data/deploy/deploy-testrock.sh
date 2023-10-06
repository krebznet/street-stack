
echo "Building Data Servce Service"
cd ..
mvn clean install
cd projects/dunkware-trade-service-data-server
echo "Deploying Data Servce Service"
docker build --platform=linux/amd64 -t dunkware-trade-service-data-server:latest .
docker tag dunkware-trade-service-data-server:latest testrock1:31000/dunkware-trade-service-data-server:latest
docker push testrock1:31000/dunkware-trade-service-data-server:latest
