# AgregadorFinanceiro

Building 

## 💻 Requirements

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
| POST                    | http://localhost:8080/users                         |
| POST(accounts)          | http://localhost:8080/users/{userId}/accounts       |
| PUT                     | http://localhost:8080/users/{userId}                |
| DEL                     | http://localhost:8080/users/{userId}                |

| Http Methods(stocks)    | URL                                                 |
| ----------------------- | --------------------------------------------------- |
| POST                    | http://localhost:8080/stocks                        |

| Http Methods(accounts)  | URL                                                 |
| ----------------------- | --------------------------------------------------- |
| GET/{accountId}         | http://localhost:8080/accounts/{accountId}/stocks   |
| POST/{accountId}        | http://localhost:8080/accounts/{accountId}/stocks   |

You can use them using a REST client such as Apidog, Postman, Insomnia, etc.

Be careful to use the correct Id and remove the `{userId}`. For example: 
> Put - http://localhost:8080/users/5321daa0-b26f-4540-a7a5-91c84ff70777

## How use Http Methods(users):

`GET` : http://localhost:8080/users

Getting all users from the Database.

Body Return:

```Json
{
        "userId": "4be3c6d0-bc94-42fd-b3c1-5d762494f444",
        "username": "brenocidade",
        "email": "teste@gmail.com",
        "password": "123",
        "creationTime": "2024-06-16T18:25:15.414011Z",
        "updatedTime": "2024-06-16T18:25:15.414011Z",
        "accounts": []
}
```
___

`GET/{userId}` : http://localhost:8080/users/4be3c6d0-bc94-42fd-b3c1-5d762494f444

Getting a user by userId from the Database.

Body Return:

```Json
{
        "userId": "4be3c6d0-bc94-42fd-b3c1-5d762494f444",
        "username": "brenocidade",
        "email": "teste@gmail.com",
        "password": "123",
        "creationTime": "2024-06-16T18:25:15.414011Z",
        "updatedTime": "2024-06-16T18:25:15.414011Z",
        "accounts": []
```
___

`GET(accounts)` : http://localhost:8080/users/4be3c6d0-bc94-42fd-b3c1-5d762494f444/accounts 

Getting a product by ID from the Database.

Body Return:

```Json
[
    {
        "accountId": "0b46d928-4db5-4ff1-9e84-487ff43cf333",
        "description": "Conta de investimento2"
    },
    {
        "accountId": "2858fc7d-ed7a-4618-bd1b-07fcfa575568",
        "description": "Conta de investimento2"
    }
]
```
___

`POST` : http://localhost:8080/users 

Creating one user within the Database.

Body: 

```Json
{
    "username": "breno",
    "email": "breno@gmail.com",
    "password": "123"
}
```
___

`POST(accounts)` : http://localhost:8080/users/4be3c6d0-bc94-42fd-b3c1-5d762494f444/accounts 

Creating one user account products within the Database.

Body: 

```Json
{
    "description": "Conta de investimento",
    "street": "Caminho17",
    "number":   500
}
```
___

`PUT/{userId}` : http://localhost:8080/users/{userId}

Updating a product by Id.

Body: 

```Json
{
    "username": "novousername",
    "password": "1234",
    "email": "testenovoemail@gmail.com"
}
```
___

`DEL/{id}` : http://localhost:8080/users/{userId}

Deleting a user. This should return an Http Status 204.

Body: 

```Json
[]                                              204 No Content
```
___

## How use Http Methods(stocks):

___

`POST` : http://localhost:8080/stocks 

Creating one stock within the Database.

Body: 

```Json
{
    "stockId": "PETR4",
    "description": "Petrobras"
}
```
___

## How use Http Methods(accounts):

___

`GET(accounts)` : http://localhost:8080/accounts/2858fc7d-ed7a-4618-bd1b-07fcfa575356/stocks

Getting a list associate stock by accountId from the Database.

Body Return:

```Json
[
    {
        "stockId": "PETR4",
        "quantity": 100,
        "total": 3745.0000000000005
    }
]
```
___

`POST` : http://localhost:8080/accounts/2858fc7d-ed7a-4618-bd1b-07fcfa575356/stocks

Associate stock with an account in the database.

Body: 

```Json
{
    "stockId": "PETR4",
    "quantity" : 100
}
```
___


## 📫 Contributing to <project_name>

To contribute to <project_name>, follow these steps:

1. Fork this repository.
2. Create a branch: git checkout -b <branch_name>.
3. Make your changes and commit them: git commit -m '<mensagem_commit>'
4. Push to the original branch: git push origin <project_name> / <location>
5. Create a pull request.

For more details, see the GitHub documentation on [how to create a pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request ).

## 🤝 Collaborators

We thank the following people who have contributed to this project:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/BrenoCidade" title="GitHub">
        <img src="https://avatars.githubusercontent.com/u/121961040" width="100px;" alt=""/><br>
        <sub>
          <b>Breno Cidade</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
