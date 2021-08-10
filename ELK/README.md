# elk-stack-logging-example
How to perform centralize logging in microservice architecture using ELK Stack

###### Download ELK Binary Distrubution

###### 1.Elastic Search [Download](https://www.elastic.co/downloads/elasticsearch).
###### 2.Logstash [Download](https://www.elastic.co/downloads/kibana).
###### 3.Kibana [Download](https://artifacts.elastic.co/downloads/logstash/logstash-7.6.2.zip).

### ELK Setup:

##### 1. Start elastic search by running elastic search in bin folder. Access http://localhost:9200/. 
##### 2. Navigate to kibana/config/kibana.yml & uncomment elasticsearch.hosts line to listen to elastic search. 
##### 3. Start kibana.sh in bin folder & access it in http://localhost:5601/app/home#/
##### 4. Specify logfile path in application.yaml file in microservices
##### 5. Create logstash.conf file to specify the log path, filtering, elastic search URL etc(Instruction is https://www.elastic.co/downloads/logstash.). It pushes log file from log directory/path to elastic search. Copy it to logstash/bin & run logstash -f logstash.conf
##### 6. Access the app url & check the logstash cmd prompt which will display all the logs in cmd.
##### 7. Navigate to "http://localhost:9200/_ca" to see all details and navigate to "http://localhost:9200/_ca/indices". You will see logstash-2021.08.10-000001(logstash-<DATE>-<INDEX>). Copy it & access http://localhost:9200/logstash-2021.08.10-000001/_search to get log details. We can specify index in logstash.conf file as well(https://stackoverflow.com/questions/33820478/how-to-create-multiple-indexes-in-logstash-conf-file). Default index name is logstash.
##### 8. Create new index pattern via "Management -> stack management -> Index pattern". Provide index pattern as "logstash-*"
##### 9. Navigate to "Analytics->Discover" where you can see all our microservices log
