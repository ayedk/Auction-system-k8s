# Real time Acution system in kubernetes
The goal of this project is to bring the power of kubernetes into the light by making a simple proof of concept application **Real time Acution system** that we will deploy it into kubernetes cluster Minikube and see how kubernetes manage Scalability,Configuration,Logging,Back up and Monitring of the application so this repostrie contain a bunch of yamls files to help you setup a kafka broker in kubernetes with a simple kafka ui to manage kafka Topics and a deployment yaml file of a spring boot resful api that produce custom json object to kafka broker as example: {"buyer_id":"456","price":"863","product_id":"22"}.

# Steps You need to Follow to get the kubernetes cluster running :
* 1-Make sure that your virtualisation is activated in the bios level
* 2-Download and install Minikube https://minikube.sigs.k8s.io/docs/start/
  * Add the minikube.exe and kubectl.exe path to the envireement variable
  * Make sure that kubectl works correctly
* 3-Deploy kubernetes dashboard to easily manage kubernetes objects
  * 3.1-Deploye the dashboard-adminuser.yaml file that will create an admin user role associate to the kubernates dashboard using the command bellow
    * ```kubectl apply -f dashboard-adminuser.yaml```
  * 3.2-Generate an access token using the command bellow
  * ```kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}"```
  * 3.2-Deploy the recommended yaml file that will deploy the kubernetes dashboard
    * ```kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0/aio/deploy/recommended.yaml```
  * 3.3-Run the kubernetes dashbord by using the command bellow
    * ```kubectl proxy```
  * 3.4-Navigate to http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/ and login using the access token
* 4-Deploy all the workload inside the project folder using the command bellow
  * ```kubectl apply -k project/```
* 5-Finally you will get smoething like this
![image](https://user-images.githubusercontent.com/40581620/111620859-8bf1b200-87e7-11eb-875b-9102a2a31f38.png)
* 6-Expose Kafka ui by running the bellow command and navigate to http://localhost:9000
  * ```kubectl --namespace default port-forward svc/kafka-ui 9000```
