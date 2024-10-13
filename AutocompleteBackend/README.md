# Autocomplete Backend

This is the backend api that provides an autocomplete feature using Java and Mysql. It also has a list of ~350k English Words that can be used to test the autocomplete feature.

## Table of Contents
 
- [Running Locally](#running-locally)
- [Running Locally With Docker](#running-locally-with-docker)
- [Testing](#testing)
- [Main Functionalities](#main-functionalities)


## Running Locally

It's mandatory to have a MySql database running locally. You can use the following command to start a MySql database using Docker. It will run the project on port 4567 and the database on port 3306.

```bash
docker-compose up --build
```

After the database is running, ** it's necessary to create the database and the table **. You can use the following script to run the migrations and populate the database with some data.

```bash
./db/migration.sh
```

## Testing

```bash
mvn test
```


## Main Functionalities

### Architecture
This project uses a combination of Domain Driven Design and Clean Architecture. The project is divided into three main layers: Application, Domain, and Infrastructure.

### Caching Layer
The project uses Java Caffeine for caching to improve performance by reducing the load on the MySQL database. The cache layer sits between the application and the database, storing frequently accessed data to minimize database queries and it is invalidated when the data is updated.