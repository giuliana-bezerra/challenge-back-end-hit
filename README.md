<h1 align="center">
  Challenge Back-End - Star Wars API
</h1>

<p align="center">
  <a href="#-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-solution">Solution</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuration">Configuration</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-build-and-run">Build and Run</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=License&message=MIT&color=8257E5&labelColor=000000">
  <img src="https://img.shields.io/static/v1?label=Tag&message=Challenge&color=8257E5&labelColor=000000" alt="Challenge" />
</p>

<br>

## ✨ Technologies

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Springdoc](https://github.com/springdoc/springdoc-openapi)
- [Mysql](https://dev.mysql.com/downloads/mysql/)
- [H2](https://www.h2database.com/html/main.html)
- [Spring Testing](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testing-introduction)

## 💻 Project

API for providing information about Star Wars franchise.
This project was created to solve the challenged proposed in [this repository](https://github.com/AmeDigital/challenge-back-end-hit).

## 💡 Solution

The challenge was solved as follows:

- TDD for developing each functionality
- Controller for managing planet resource
- Spring Data for saving planet data (H2 database for tests and Mysql for development environment)
- Swagger documentation for the API
- Bean validation for bad request scenarios
- Exception handlers to standartize responses
- Calls to an [external API](https://swapi.co/) to query Star Wars data

## 🛠️ Configuration

You have to configure a mysql database for persisting data as folows:

```
$ sudo mysql

CREATE USER 'user'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;

exit

$ mysql -u user -p

CREATE DATABASE starwars;

exit
```

## 🚀 Build and Run

For building and testing (unit and integration), execute the script:

```sh
$ ./build-and-test.sh
```

For building and running, execute the script:

```sh
$ ./build-and-run.sh
```

The APIs will be available in Swagger-UI: http://localhost:8080/swagger-ui.html.
