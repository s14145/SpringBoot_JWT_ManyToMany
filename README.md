# Spring Boot REST API using Many-To-Many Relationship &amp; JWT

This application starts in port 6063.

The technologies used to build this application are:

1. Spring Boot Web

2. Spring Boot Data JPA

3. Spring Boot Devtool

4. H2 Database

5. Lombok

6. Spring Boot Security

7. Hibernate 

8. Jackson 

9. JWT (JSON Web Token)


H2 Database URI: http://localhost:6063/h2-console

**The REST API endpoints exposed are**

1. POST Register: http://localhost:6063/api/v1/auth/register

2. POST Authenticate: http://localhost:6063/api/v1/auth/authenticate

**Sample POST Register Request Payload:**

{

    "firstname": "Ram",
    "lastname": "Poudel",
    "email": "RamPoudel@gmail.com",
    "password": "abcd1234",
    "roles": [
        {
            "name": "USER"
        },{
            "name": "ADMIN"
        }
    ]
    
}


**Sample POST Authenticate Request Payload:**

{

    "email": "RamPoudel@gmail.com",
    "password": "abcd1234"
    
}


