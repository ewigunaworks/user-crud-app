# User CRUD Apps

### RUNNING THE APPS
To run the code, you can use
```
    mvn spring-boot:run
```
or
```
    mvn install
    cp target/sawitpro-0.0.1-SNAPSHOT.jar src/main/docker
    cd ./docker
    docker-compose up -d
```

### HOW TO USE
1. POST - /api/auth/signup
    body: {
        "name": "",
        "phoneNumber": "",
        "password" : ""
    }
2. POST - /api/auth/signin
    body: {
        "phoneNumber": "",
        "password" : ""
    }
3. GET - /api/user/get-name
    Authorization: bearer <token>
4. POST - /api/user/update
    Authorization: bearer <token>
    body: {
        "name": ""
    }

### BUILT WITH

[Spring-Boot](https://spring.io/) - Level up your Java™ code. With Spring Boot in your app, just a few lines of code is all you need to start building services like a boss.
[PostgreSQL](https://www.postgresql.org/) - PostgreSQL is a powerful, open source object-relational database system that uses and extends the SQL language combined with many features that safely store and scale the most complicated data workloads. 