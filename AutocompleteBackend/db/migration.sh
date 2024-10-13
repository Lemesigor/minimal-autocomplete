#!/bin/bash -x
root_dir="$(realpath "$(dirname "$0")/..")"
db_dir="$root_dir/db"
db_url="jdbc:mysql://localhost:3306/autocomplete?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true"
db_properties="$db_dir/liquibase.properties"
err=output.err


cd $db_dir


echo "Running liquibase update"
./liquibase-3.5.3-bin/liquibase --url="$db_url" --defaultsFile=$db_properties update

cat $err

echo "Liquibase Update finished"