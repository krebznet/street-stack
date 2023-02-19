cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-tick-server
sleep 1
docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/tickservice:latest .


cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-net-cluster-server
sleep 1
docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/clusterservice:latest .


cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-stream-server
sleep 1
docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/streamservice:latest .


cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/projects/dunkware-trade-service-worker-server
sleep 1
docker buildx build --push  --platform linux/amd64  --tag testrock1.dunkware.net:31000/workerservice:latest .
