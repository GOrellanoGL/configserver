#Run config-server:
* cd config-service
* mvn clean package
* java -jar target/config-server-0.0.1-SNAPSHOT.jar

#Run bank account service:
* cd bank-account-service
* mvn clean package
* java -jar target/bank-account-service-0.0.1-SNAPSHOT.jar

#Tests:

config-server:
* http://localhost:8888/bank-account-service/dev
* http://localhost:8888/bank-account-service/default
* http://localhost:8888/bank-account-service/uat

bank-account-server:
#### curl -i -H "Content-Type: application/json" -X POST -d '{"accountId":"B12345","accountName":"Joe Bloggs","accountType":"CURRENT_ACCOUNT","accountBlance":1250.38}' localhost:8080/bank-account
