# Лабораторная работа 1


## API Эндпоинты

### Методы доступные для всех пользователей

#### 1. Регистрация пользователя

**Запрос**
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "username",
  "password": "FL*48a56_32"
}
```
**Ответ**
```plain
User username registered successfully
```
#### 2. Авторизация пользователя
**Запрос**

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
```
**Ответ**
```json
{
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTc2MDQ0MzE3MiwiZXhwIjoxNzYwNTI5NTcyfQ.e6gVTdt_5scUrzj3Fhh42TEdaLat8gsHwArF8OSz0Cc27MDOrvtXpTxBDUZ2w1LD"
}
```

### Методы доступные только авторизованным пользователям
#### 1. Получение цены инструмента по тикеру

**Запрос**

```http
GET /api/price/XAU
Content-Type: application/json
Authorization: Bearer {token}
```
**Ответ**

```json
4128.0
```

#### 2. Получение цен всех доступных инструментов
**Запрос**

```http
GET /api/data
Content-Type: application/json
Authorization: Bearer {token}
```
**Ответ**

```json
{
    "BTC": 111581.46875,
    "ETH": 3978.870117,
    "XAU": 4128.0,
    "XPD": 1512.0,
    "XAG": 51.507,
    "HG": 4.798629
}
```

## Реализованные методы защиты
### 1. Аутентификация по JWT
- JWT токен с алгоритмом подписи HS256 для аутентификации. При каждом запросе к защищенным эндпоинтам происходит валидация токенов.
### 2. Защита от SQL-инъекций
- В проекте используется Spring Data



## Скриншоты отчетов

### SAST

![SAST review](./.img/spotbugs.png)

### SCA

![SCA review](./.img/depcheck.png)