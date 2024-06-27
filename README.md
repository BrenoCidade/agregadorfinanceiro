Building 

## Requirements

1. Java - 21
2. Maven - 3.x.x
3. MySQL - 5.x.x

## Steps to Setup

### 1. Clone the repository

> git clone https://github.com/BrenoCidade/agregadorfinanceiro

### 2. Create MySQL Database

> CREATE DATABASE mysqldb;

### 3. Change MySQL Username and Password as per your installation

* Open `agregadorfinanceiro/src/main/resources/application.properties`
* Change `spring.datasource.username` and `spring.datasource.password` as per your MySQL Credentials.

### 4. Start the application via Maven package or Start it using Spring Boot Application as default:
 `agregadorfinanceiro/src/main/java/com/brenocidade/agregadorfinanceiro/AgregadorfinanceiroApplication.java`

### 5. The App will start running at http://localhost:8080/

___

## Explore HTTP Methods:

| Http Methods(users)     | URL                                                 |
| ----------------------- | --------------------------------------------------- |
| GET                     | http://localhost:8080/users                         |
| GET/{userId}            | http://localhost:8080/users/{userId}                |
| GET(accounts)           | http://localhost:8080/users/{userId}/accounts       |
| POST(accounts)          | http://localhost:8080/users/accounts                |
| POST                    | http://localhost:8080/users/{userId}/accounts       |
| PUT                     | http://localhost:8080/users/{userId}                |
| DEL                     | http://localhost:8080/users/{userId}                |

| Http Methods(stocks)    | URL                                                 |
| ----------------------- | --------------------------------------------------- |
| POST                    | http://localhost:8080/stocks                        |

| Http Methods(accounts)  | URL                                                 |
| ----------------------- | --------------------------------------------------- |
| GET/{accountId}         | http://localhost:8080/accounts/{accountId}/stocks   |
| POST/{accountId}        | http://localhost:8080/accounts/{accountId}/stocks   |
