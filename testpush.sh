echo "Tick Service"
cd projects/dunkware-street-tick/projects/dunkware-trade-service-tick-server
./test.sh
cd ../../../../
echo "Street Gateway" 
cd projects/time-gateway-srvc
./test.sh
cd ../
cd time-worker-srvc
./test.sh
cd ../
cd time-stream-srvc
./test.sh
cd ..
cd time-injest-srvc
./test.sh
cd ..
cd time-stats-srvc
./test.sh