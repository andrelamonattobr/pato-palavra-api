# Pato Palavra

A Spring Boot application for word-based games and tools. The application serves as a backend for working with a database of words.

## Overview

Pato Palavra is built using:
- Java 24
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security
- JWT for authentication
- PostgreSQL database

## Database Setup

### Requirements

The application requires a PostgreSQL database to run. You can use the included Docker Compose file to set up the database:

```bash
docker-compose up -d
```

This will start a PostgreSQL instance with the following configuration:
- Username: patolino
- Password: pato
- Database name: pato-palavra-db
- Port: 5432

### Database Population

The tests require a populated database. The repository includes a file named `palavras.txt` containing more than 2000 words that need to be manually imported into the database. 

To populate the database:
1. Start the PostgreSQL database
2. Create a table named `words` with a column `word`
3. Execute the SQL statements in the `palavras.txt` file to insert the words into the database

## Development

To run the application locally:

```bash
./mvnw spring-boot:run
```

## Testing

To run the tests:

```bash
./mvnw test
```

Note: Tests require a connection to the database populated with words from the `palavras.txt` file. 