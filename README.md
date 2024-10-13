# Minimalist Autocomplete

This repository contains the source code for an autocomplete application, including both frontend and backend components. The project uses Docker and Docker Compose for containerization and easy setup.


## Tech Stack
- **Frontend**: React with Vite, TypeScript, Emotion
- **Backend**: Java 
- **Database**: MySql

## Prerequisites

- Docker: [Install Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Install Docker Compose](https://docs.docker.com/compose/install/)

## Running the Application

### Step 1: Clone the Repository

```sh
git clone <repository-url>
cd <repository-directory>
```

### Step 2: Build and Run the Application With Docker Compose
1. Ensure Docker and Docker Compose are installed and running on your machine.
2. Navigate to the root directory of the repository.
3. Run the following command to build and start the containers:

```sh
docker-compose up --build
```

### Step 3: Run the Database Migrations
**VERY IMPORTANT:** 
 After starting the backend containers, you need to run the migration.sh script to set up the database tables and seed the data. Without this step, the database will not have the necessary tables and data. *If it is not done, the application will not work as expected.*
It will also populate the database with ~350K words from the `AutocompleteBackend/db/terms.csv` file.
 Navigate to the root directory of the repository and run the following command:

```sh
sh ./AutocompleteBackend/db/migration.sh
```

### Step 4: Access the Application
- The frontend application is accessible at [http://localhost:3000](http://localhost:3000).
- The backend API is accessible at [http://localhost:4567](http://localhost:4567).


## General Information

For detailed information about the frontend and backend components, see the respective README files in the `autocomplete-frontend` and `AutocompleteBackend` directories.

## API Endpoints
- **GET /autocomplete**: The main endpoint that returns a list of autocommplete based on the provided query string. The query string is passed as a URL parameter `query`. Example: `http://localhost:4567/suggestions?query=java&limit=10`.

### Example Request

```sh
curl -X GET "http://localhost:4567/suggestions?query=java&limit=10" -H  "accept: application/json"
```

### Example Response
```json
{
"total": 2,
  "terms": [
    {
      "id": 1,
      "value": "Java"
    },
    {
      "id": 2,
      "value": "JavaScript"
    }
  ]
}
```
- **POST/ autocomplete**: The endpoint that allows you to add a new term to the database. The term is passed as a JSON object in the request body. Example: `http://localhost:4567/autocomplete`.

### Example Request

```sh
curl -X POST "http://localhost:4567/autocomplete" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"term\":\"Java\"}"
```

### Example Response
```json
{
  "id": 3,
  "value": "Java",
}
```

- **DELETE /autocomplete**: The endpoint that allows you to delete a term from the database. The term is passed as a JSON object in the request body. Example: `http://localhost:4567/autocomplete/:id`.

### Example Request

```sh
curl -X DELETE "http://localhost:4567/autocomplete/3" -H  "accept: application/json"
```