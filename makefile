CONATINER_LIST=$(shell docker ps -a -q)
VOLUME_LIST=$(shell docker volume ls -q)

.PHONY: docker-volume-reset kafka-setup run-app

delete-containers:
	docker rm -f ${CONATINER_LIST}

volume-reset:
	docker volume rm ${VOLUME_LIST}

kafka-setup: docker-volume-reset
	cd setup; docker-compose up -d

kafka-stop:
	cd setup; docker-compose down

run-app:
	cd /home/e947263/kafka ; /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java -Dfile.encoding=UTF-8 @/tmp/cp_4hc3um9y5zzyzqspm0yskrdv1.argfile com.example.kafka.KafkaApplication