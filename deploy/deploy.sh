cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud
mvn clean install
cd /Users/duncankrebs/dunkware/street/cloud/1.0.0/dunkware-street-cloud/deploy
./dockerpush.sh
ssh mradmin@testrock1.dunkware.net << EOF
   cd /home/mradmin/k8s/street/cloud
   ./destroy.sh
   sleep 10
   ./deploy.sh
EOF
