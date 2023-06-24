ssh mradmin@testrock1.dunkware.net << EOF
   cd /home/mradmin/k8s/street/cloud/cluster
   ./destroy.sh
   sleep 10
   ./deploy.sh
EOF
