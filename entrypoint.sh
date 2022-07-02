#!/bin/sh

DB_USER=$(cat /secrets/db-user)
DB_PWD=$(cat /secrets/db-pwd)
JWT=$(cat /secrets/jwt-secret)
BUCKET=$(cat /secrets/s3-bucket)

java -jar -DSPRING_DATASOURCE_URL=jdbc:mysql://social-mysql.default.svc.cluster.local/api-db?useSSL=false \
-DSPRING_DATASOURCE_USERNAME="$DB_USER "\
-DSPRING_DATASOURCE_PASSWORD="$DB_PWD" \ 
-DJWT_SECRET="$JWT" \
-DS3_BUCKET="$BUCKET" \ 
-Dlogging.level.root=INFO \
-Dspring.datasource.hikari.maximum-pool-size=5 \
social-network-api-1.0-SNAPSHOT.jar
            