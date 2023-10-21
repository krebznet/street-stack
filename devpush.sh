echo "worker server"
./projects/dunkware-trade-service-worker-server/deploy/dev.sh
echo "strem server"
./projects/dunkware-trade-service-stream-server/deploy/dev.sh
echo "web servier "
./projects/dunkware-trade-service-web-server/deploy/dev.sh
echo "dat server"
./projects/dunkware-street-data/projects/dunkware-trade-service-data-server/deploy/dev.sh
echo "tick server"
./projects/dunkware-street-tick/projects/dunkware-trade-service-tick-server/deploy/dev.sh
echo "trade servier"
./projects/dunkware-street-beach/modules/dunkware-trade-initializer/deploy/dev.sh
