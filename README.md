# Getting Started

# Movie Application

Simple API for managing movies and their associated actors. It uses an in-memory H2 database and provides endpoints for creating, reading, and deleting records.

## Technologies

- Spring Boot
- Spring Data JPA
- Hibernate with H2 Database
- Lombok

## Setup

### Prerequisites:
- JDK 17
- Maven

### Run the Application

To run the application, use the following command in the terminal at the root of your project:

Shell  `mvn spring-boot:run`

# Usage

The application provides the following REST API endpoints:

- `GET /api/movies`: Fetches all movies.
- `GET /api/movies/{id}`: Fetches a specific movie by its ID.
- `POST /api/movies`: Creates a new movie.
- `DELETE /api/movies`: Deletes all movies.

When creating a new movie, make sure to provide the necessary information in the following format:
```json
{
  "title": "string",
  "description": "string",
  "actors": [
    {
      "lastName": "string",
      "firstName": "string"
    }
  ]
}
```
Example: 
``` json
{
  "title":"Star Wars: The Empire Strikes Back",
  "description":"Darth Vader is adamant about turning Luke Skywalker to the dark side.",
  "actors":[
    {
      "lastName":"Ford",
      "firstName":"Harrison"
    },
    {
      "lastName":"Hamill",
      "firstName":"Mark"
    }
  ]
}
```

