# Book-Store (Reading is Good)

### About The Project
ReadingIsGood is an online books retail firm which operates only on the Internet.  Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day.

### Summary

In this project;

- Each module has three letter aliases.  Every classes or interfaces that related with it start with these alliases.
- Dependencies are managed by Maven.
- Every entities are logged with Hibernate Envers. 
- Endpoints are secure with JWT
- Every request is validated with Spring Validation
- Documented with Swagger.
- Endpoints use DTOs instead of entities. Convert operations are done with mapstruct.
- Tests were written with Junit&Mockito.
- Lombok was used. 
- The structure of the all responses are same. It can be understood from the isSuccess field that the requests are successful or unsuccessful.

Module  | Allias
------------- | -------------
Customer  | Cus
Production  | Prd
Order  | Ord 
Security  | Sec 
Generic  | Gen 


### Technologies
- Java 11
- PostgreSQL
- Spring Boot 
- Maven
- Junit, Mockito
- Swagger
- Lombok
- Mapstruct

### Prerequisites
- Java 11 or never
- Docker Desktop

# Installation
1. Clone the repo

```sh
https://github.com/sbahadirm/book-store.git
```


2. Run Docker-Compose file 
```powershell
 > docker-compose up
```
   
