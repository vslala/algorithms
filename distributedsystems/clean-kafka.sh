#!/bin/bash

echo "Cleaning Kafka topics..."

# Delete topics
echo "Deleting topics..."
kafka-topics --bootstrap-server localhost:9092 --delete --topic web_crawler_jobs
kafka-topics --bootstrap-server localhost:9092 --delete --topic url_frontier

# Wait a bit for deletion
sleep 2

# Recreate topics
echo "Recreating topics..."
kafka-topics --bootstrap-server localhost:9092 --create --topic web_crawler_jobs --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic url_frontier --partitions 3 --replication-factor 1

echo "Done! Topics cleaned and recreated."

# List topics to verify
kafka-topics --bootstrap-server localhost:9092 --list
