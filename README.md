# Booking management
Management of customers bookings and blocks.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
- Java 17 or higher
- Maven

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [H2 Database](https://www.h2database.com/html/main.html) - In-memory database
- [JPA](https://www.oracle.com/java/technologies/persistence-jsp.html) - Java Persistence API
- [Swagger](https://swagger.io/) - OpenAPI Documentation

## Initial Data
After setting up the project and it starts successfully on port 8080, the initial data will be loaded through resources/data.sql file:

```sql
INSERT INTO guest (name) VALUES
('Joseph'),
('Antony'),
('Carl'),
('Mary'),
('Michael'),
('Amy');

INSERT INTO owner (name) VALUES
('Robert'),
('Angela'),
('Dave');

INSERT INTO property (id_owner, name) VALUES
(1, 'House'),
(2, 'Flat'),
(3, 'Flat'),
(1, 'Mansion');

INSERT INTO property_blocks (id_property, start_date, end_date, notes) VALUES
(1, '2023-11-01', '2023-11-10', 'Repaint house'),
(2, '2023-01-01', '2023-01-10', 'Repair floor'),
(3, '2023-01-01', '2023-01-10', 'Repair front window'),
(1, '2023-01-01', '2023-01-10', 'Staying there for the weekend');
```

### H2 Console

The H2 console is enabled for this project:
```yaml 
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
You can access the tables and perform queries at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Swagger UI

The Swagger UI is integrated with this project:
```yaml 
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui
```
All endpoints of this project are available at [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)