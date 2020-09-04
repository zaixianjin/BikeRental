cd E:\_MyRepo\kafka_2.12-2.6.0\bin\windows

kafka-topics.bat --zookeeper localhost:2181 --topic topicTest --create --partitions 1 --replication-factor 1

kafka-topics.bat --zookeeper localhost:2181 --list

pause