# Тестовое задание

Спроектировать простую БД, обеспечивающую хранение информации о клиентах и их контактой информации.
Каждый клиент характеризуется именем.
Каждому клиенту в соответствие может быть поставлена информация о его контактах: 0 и более телефонных номеров, 0 и более адресов электронной почты.
***
Server url:
`http://localhost:8081`  
Базовый path:`/unibellTT/api`

Swagger UI: `http://localhost:8081/swagger-ui/index.html`  
Swagger doc: `http://localhost:8081/v3/api-docs`

##**Endpoints:**

**POST /createClient** - добавить нового клиента
- *id* - идентификатор клиента
- *name* - имя клиента

Пример: `http://localhost:8081/unibellTT/api/createClient`

`Request body`
```json
{
  "name": "string"
}
```
`Response body`
```json
{
  "id": 1073741824,
  "name": "string"
}
```
***
**POST /{id}/addContact** - добавить новый контакт клиента  

- *id* - path param - идентификатор клиента
- *contact* - значение контакта (телефон или email)
- *type* - тип контакта (допустимые значения: EMAIL, PHONE)

`Request body`
```json
{
  "contact": "string",
  "type": "email"
}
```
`Response body`
```json
{
  "contact": "string",
  "type": "string"
}
```
***
**GET /clients** - получить список клиентов  
- *id* - path param - идентификатор клиента
- *name* - имя клиента

`Response body`
```json
[
  {
    "id": 1073741824,
    "name": "string"
  }
]
```
***
**GET /{id}/clientInfo** - получить информацию по заданному клиенту
- *id* - pathParam - идентификатор клиента
- *name* - имя клиента
- *contacts* - список контактов
  - *contact* - значение контакта (телефон или email)
  - *type* - тип контакта (допустимые значения: EMAIL, PHONE)


`Response body`
```json
{
  "id": 1073741824,
  "name": "string",
  "contacts": [
    {
      "contact": "string",
      "type": "phone"
    }
  ]
}
```
***
**GET /{id}/contracts** - получить список контактов заданного клиента
- *id* - pathParam - идентификатор клиента
- *contact* - значение контакта (телефон или email)
- *type* - тип контакта (допустимые значения: EMAIL, PHONE)

`Response body`
```json
[
  {
    "contact": "string",
    "type": "phone"
  }
]
```
***
**GET /{id}/contacts/{type}** - получить список контактов заданного типа заданного клиента
- *id* - pathParam - идентификатор клиента
- *contact* - значение контакта (телефон или email)
- *type* - тип контакта (допустимые значения: EMAIL, PHONE)

`Response body`
```json
[
  {
    "contact": "string",
    "type": "email"
  }
]
```
***
Использованные технологии:
- [Java 17]
- [Maven]
- [Spring Boot]
- [Google Lib Phone Number]

---
![[java 17]](https://img.shields.io/static/v1?label=Java&message=17&color=007396&style=for-the-badge&logo=java)
![[maven]](https://img.shields.io/static/v1?label=Maven&message=3.6&color=C71A36&style=for-the-badge&logo=apachemaven)
![[spring boot]](https://img.shields.io/static/v1?label=Spring+Boot&message=3.4.2&color=6DB33F&style=for-the-badge&logo=springboot)
![[spring boot]](https://img.shields.io/static/v1?label=Google+PhoneNumber&message=8.13.54&color=1b5da8&style=for-the-badge&logo=google)
![[spring boot]](https://img.shields.io/static/v1?label=lombok&message=8.13.54&color=962612&style=for-the-badge&logo=lombok)

[java 17]:<https://www.oracle.com/ru/java/technologies/javase-downloads.html>
[maven]:<https://maven.apache.org/download.cgi>
[spring boot]:<https://www.baeldung.com/spring-with-maven>
[google lib phone number]:<https://github.com/google/libphonenumber/blob/master/README.md>