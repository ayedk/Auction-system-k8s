# bidding-systeme-in aszezsszs
This a repostrie contain a banch of yamls files to setup a kafka broker in kubernetes with a simple kafka ui to manage kafka Topics and also it hase a deployment yaml file
of a bidding-system-producer 

*Steps
*1-Make shure that your virtualisation is activated in the bios level
*2-download and install Minikube https://minikube.sigs.k8s.io/docs/start/
  *Add the minikube.exe and kubectl.exe path to the envireement variable
  *Make shure that kubectl
*3-Deploy kubernetes dashboard to easly manage kubernetes objects
  *3.1-Create admin based role to the dasboard
    *Deploye the dashboard-adminuser.yaml file that will create an admin user role associate to the kubernates dashboard using the command bellow
    *kubectl apply -f dashboard-adminuser.yaml
  *3.2-Deploye the recommmended yaml file that will deploy the kuberntes dashboard
    *kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0/aio/deploy/recommended.yaml
  *3.3-Run the kubernetes dashbord by using the command bellow and 
    *kubectl proxy
  *3.4-Navigate to http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/
*4-Deploy all the workload inside the project folder using the command bellow
  *kubectl apply -k project/
*5-Finally you will get smoething like this
*![image](https://user-images.githubusercontent.com/40581620/111620859-8bf1b200-87e7-11eb-875b-9102a2a31f38.png)
