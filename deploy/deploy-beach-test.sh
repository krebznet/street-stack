#echo "Executing Maven Build"
cd $STREET_CLOUD_HOME


echo "Deploying Beach  Service"

cd ${STREET_CLOUD_HOME}/projects/dunkware-trade-service-beach-server
docker build --platform=linux/amd64 -t dunkware-trade-service-beach-server:latest .
docker tag dunkware-trade-service-beach-server:latest testrock:31000/dunkware-trade-service-beach-server:latest
docker push testrock1:31000/dunkware-trade-service-beach-server:latest
