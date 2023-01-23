ssh mradmin@testrock1.dunkware.net << EOF
   cd /home/mradmin/k8s/street/cloud
   ./destroy.sh
   sleep 10
   ./deploy.sh
EOF
