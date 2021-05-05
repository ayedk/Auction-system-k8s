# Real Time Auction System in kubernetes
## Project Discription :
The goal of this project is to build a cloud architecture for the Stream Gateway to be cloud ready so i have done a research about cloud services and I found that Kubernetes is the most used service for containerized application provided by almost every cloud provider such as AWS has [Amazon Elastic Kubernetes Service EKS](https://aws.amazon.com/eks/), Azure has [Azure Kubernetes Service AKS](https://azure.microsoft.com/en-in/services/kubernetes-service/) and Google Cloud has [Google Kubernetes Engine GKE](https://cloud.google.com/kubernetes-engine) and due to the confidential of the Stream Gateway i have build a proof of concept application **Real Time Auction system** that has similar functionality of the Stream Gateway and we will deploy it into a kubernetes cluster Minikube and see how kubernetes manage the application Scalability, Configuration, Logging, Backup and Monitoring.

## PoC Discription :
The PoC application is an Online Real Time Auction System that allows sellers to sell their stuff and buyers to place bids on the auctions and after the auction is close the owner of the biggest bid price wins the auction.
## PoC Architecture :
![PoC Architecture](https://user-images.githubusercontent.com/40581620/115128437-a7fa9600-9fd5-11eb-87c8-a8cb2a08341a.png)

**In this architecture there are three main services :**
* **Up Stream Service** : This is the service how responsible for taking placed bids from buyers and producing bids event to Kafka and consuming notification events from Kafka then send them to user via web socket
* **Stream Api Service** : Wich is the service that has a similar functionality to the Stream Gateway that take the bid from the up stream and publish them to the down stream service via kafka and take notification events from Down Stream and publish them to Up Stream service.
* **Down Stream Service** : This service is responsible for taking bid event from the Api Service and do a search on the database, then publish back notification events to the buyers that already placed a bid in the same auction.
## Repository Content :
* **helm chart** :
This folder contains  the helm chart that we will use to deploy the PoC application to kubernetes cluster.
* **spring boot services** :
The three folders up-stream, stream-api and the down-stream is the services for the PoC developed using spring boot.
* **script** :
The script folder is contains a javascript script that generates random bid and send them to up stream service using http module.
* **kafka-ui** :
This folder contains a kafka ui kubernetes deployment and service that we will use to visualise kafka broker.
## Kubernetes Learning Resources :
* kubernetes official documentation : https://kubernetes.io/docs/home/
* Youtube Chanel:
  * Tech World with Nana : https://www.youtube.com/c/TechWorldwithNana
  * Kode Kloud : https://www.youtube.com/channel/UCSWj8mqQCcrcBlXPi4ThRDQ
* Udemy Courses :
  * Kubernetes for the Absolute Beginners - Hands-on : https://www.udemy.com/course/learn-kubernetes
  * Docker and Kubernetes: The Complete Guide : https://www.udemy.com/course/docker-and-kubernetes-the-complete-guide
* Kubernetes online labs :
  * Play with kubernetes : https://labs.play-with-k8s.com/
  * Kubernetes Playground : https://www.katacoda.com/courses/kubernetes/playground

## Prerequiset :
* Kubernetes Cluster
* Helm
## Steps You need to Follow to get the kubernetes cluster running :
* 1-Make sure that your virtualisation is activated in the bios level
* 2-Download and install Minikube https://minikube.sigs.k8s.io/docs/start/
  * Add the minikube.exe and kubectl.exe path to the envireement variable
  * Make sure that kubectl works correctly
* 3-Deploy kubernetes dashboard to easily manage kubernetes objects
  * 3.1-If the command bellow doesn't work for you try to follow the further steps
    * ```minikube dashboard```
  * 3.2-Deploying kubernetes dashboard using yaml file:
    * -Deploye the dashboard-adminuser.yaml file that will create an admin user role associate to the kubernates dashboard using the command bellow
    * ```kubectl apply -f dashboard-adminuser.yaml```
    * -Generate an access token using the command bellow
    * ```kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token |    base64decode}}"```
    * -Deploy the recommended yaml file that will deploy the kubernetes dashboard
    * ```kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0/aio/deploy/recommended.yaml```
    * -Run the kubernetes dashbord by using the command bellow
    * ```kubectl proxy```
    * -Navigate to http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/ and login using the access token
## Steps You need to Follow to get Helm installed:
* 1-Download your [desired version](https://github.com/helm/helm/releases).
* 2-Unpack it.
* 3-Find the helm binary in the unpacked directory, and add it to the envirement variabale path so you can execute the helm command from any terminal.
## Usage
**Clone this repositry and follow the further steps** :
* Create a kubernetes cluster :\
**```minikube start --memory=5g```**
* Install the helm chart by switching to the helm-chart directory an running the following command :\
**```helm install auction-system ./```**
* Forward the upstream service traffic from the cluster to your host machine using the commmand below :\
**```kubectl --namespace default port-forward svc/upstream 8080```**
* Switch to the script folder and run the following command this will generate a random number of bids to the upstream service :\
**```npm install```**
**```node script.js```**
* Deploy a kafka ui so you can visualise kafka topics :\
**```kubectl apply -f ./kafka-ui```**
* Access kafka UI at http://localhost:9000 :\
**```kubectl --namespace default port-forward svc/kafka-ui 9000```**
**Setting up monitoring** :
* Setup Prometheus using helm :\
**```kubectl create namespace monitoring```**\
**```helm repo add prometheus-community https://prometheus-community.github.io/helm-charts```**\
**```helm repo add kube-state-metrics https://kubernetes.github.io/kube-state-metrics```**\
**```helm repo update```**\
**```helm install --namespace monitoring  prometheus --set server.global.scrape_interval=10s,server.global.scrape_timeout=5s prometheus-community/prometheus```**
* Access Prometheus UI at http://localhost:5000 :\
**```kubectl --namespace monitoring port-forward svc/prometheus-server 5000:80```**
* Setup Grafana using helm :\
**```helm repo add grafana https://grafana.github.io/helm-charts```**\
**```helm repo update```**\
**```helm install grafana grafana/grafana```**
* Access Grafana dashboard at http://localhost:3000 :\
**```kubectl --namespace monitoring port-forward svc/grafana 3000:80```**
* Import grafana dashbord :
