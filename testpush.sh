echo "Tick Service"
cd projects/dunkware-street-tick/projects/dunkware-trade-service-tick-server
./test.sh
cd ../../../../
echo "Street Gateway" 
cd projects/dunkware-trade-service-web-server
./test.sh
cd ../
cd dunkware-trade-service-worker-server
./test.sh
cd ../
cd dunkware-trade-service-stream-server
./test.sh
cd ..
cd dunkware-street-data/services/injest
./test.sh
cd ..
cd stats
./test.sh