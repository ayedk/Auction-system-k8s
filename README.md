# Real Time Acution System in kubernetes
## Project Discription :
The goal of this project is to build a cloud archtecture for the Stream Gateway to be cloud ready so i have did a research about cloud services and i found that kubernetes is the most used service for containerized application provided by almost every cloud provider such as AWS has [Amazon Elastic Kubernetes Service EKS](https://aws.amazon.com/eks/), Azure has [Azure Kubernetes Service AKS](https://azure.microsoft.com/en-in/services/kubernetes-service/) and Google Cloud has [Google Kubernetes Engine GKE](https://cloud.google.com/kubernetes-engine) and due to the confidantial of the Stream Gateway i have build a proof of concept application **Real Time Acution system** that has similar functionality of the Stream Gateway and we will deploy it into a kubernetes cluster Minikube and see how kubernetes manage the application Scalability, Configuration, Logging, Back up and Monitring.

## PoC Discription :
The PoC application is an Online Real Time Acution System that allow sellers to sell their stuff and buyers to place bids on the auctions and after the auction is close the owner of the biggest bid price win the auction.
## PoC Architecture :
![Blank diagram (1)](https://user-images.githubusercontent.com/40581620/114805685-55ce3080-9d9b-11eb-8eab-cb64a6b8d748.png)

![PoC Architecture](https://user-images.githubusercontent.com/40581620/114798457-4b0c9f00-9d8d-11eb-83c4-16a5a88f9330.png)
In this architecture there are three main services :
* **Up Stream Service** : This is the service how responsibale for taking placed bids from buyers and producing bids event to kafka and consuming notification events from kafka then send them to user via web socket
* **Stream Api Service** : Wich is the service that has a similar functionality to the Stream Gateway that take the bid from the up stream and publish them to the down stream service via kafka and take notification events from Down Stream and publish them to Up Stream service.
* **Sown Stream Service** : This service is responsible for taking bid event from the Api Service and do a search on the database then publish back notification events to the buyers that already placed a bid to the same auction.
## Repository Content :
* **helm chart** :

* **spring boot services** :

* **script** :
 
* **kafka-ui** :

# Kubernetes Learning Resources :
* kubernetes official documentation : https://kubernetes.io/docs/home/
* Youtube Chanel:
  * Tech World with Nana : https://www.youtube.com/c/TechWorldwithNana
  * Kode Kloud : https://www.youtube.com/channel/UCSWj8mqQCcrcBlXPi4ThRDQ
* Udemy Courses :
  * Kubernetes for the Absolute Beginners - Hands-on : https://www.udemy.com/course/learn-kubernetes
  * Docker and Kubernetes: The Complete Guide : https://www.udemy.com/course/docker-and-kubernetes-the-complete-guide
# Usage
## Steps You need to Follow to get the kubernetes cluster running :
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
* 5-Finally you will get smoething like this
![image](https://user-images.githubusercontent.com/40581620/111620859-8bf1b200-87e7-11eb-875b-9102a2a31f38.png)
# Helm install
