#!/bin/bash -x
db_url="jdbc:mysql://localhost:3306/autocomplete?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true"
db_properties="./liquibase.properties"

./liquibase-3.5.3-bin/liquibase --url="$db_url" --defaultsFile=$db_properties update

if ! grep -Fxq "Liquibase Update Successful" $err; then
  exit 1
fi