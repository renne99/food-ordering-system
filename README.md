# Food Ordering System

A Spring Boot project built as part of the Jumpstart Development Programme.

---
## Author: Asisipho

## Project Details

- **Group:** com.jumpstart
- **Artifact:** food-ordering-system
- **Java Version:** 21
- **Framework:** Spring Boot

---

## Research Questions

### 1. What is Spring Boot?
Spring Boot is a Java framework that helps developers build applications
quickly without lots of setup. It comes pre-configured so you can focus
on writing your code straight away.

### 2. What is Maven?
Maven is a tool that manages your project and automatically downloads
all the libraries your project needs. It also compiles and packages
your code when you are ready to run it.

### 3. What is the purpose of pom.xml?
The pom.xml is a file that lists everything your project needs,
like its name, Java version, and dependencies. Maven reads this file
to know how to build your project.

### 4. What is the purpose of application.properties?
This file is where you store your app's settings, like your database
username and password. It keeps your configuration separate from your code.

### 5. What does @SpringBootApplication do?
It is an annotation placed on the main class that starts the whole
Spring Boot application. It tells Spring to scan your code and
set everything up automatically.

### 6. Why do developers use dependency management tools such as Maven?
Instead of manually downloading libraries, Maven does it for you
automatically. It also makes sure all the libraries work well together.

### 7. What is a REST API?
A REST API is a way for apps to talk to each other over the internet
using simple HTTP requests. For example, when an app loads a list of
restaurants, it is calling a REST API to get that data.

### 8. What is JSON?
JSON is a simple format used to send data between a server and an app.
It looks like this:

{
"id": 1,
"name": "Pizza",
"price": 89.99
}

### 9. What is Dependency Injection?
Instead of a class creating its own objects, Spring Boot provides
them automatically. This makes your code cleaner and easier to manage.

---

## Database Setup

- **Database:** food_ordering
- **Table:** category
- **Records inserted:** Fast Food, Pizza, Drinks, Desserts

---

## Package Structure

### controller
This is where we handle incoming requests from the user or client.
It is the entry point of the application.

### service
This is where the business logic lives.
It processes data and acts as a bridge between the controller and repository.

### repository
This package talks directly to the database.
It handles all the saving, fetching, and deleting of data.

### entity
This is where we define our database tables as Java classes.
Each class in here represents a table in the database.

### dto
DTO stands for Data Transfer Object.
These are simple classes used to carry data between layers without exposing the full entity.

### config
This is where we put any special configuration for the application.
Things like security settings or custom beans go here.

### exception
This package handles errors in a clean way.
Instead of the app crashing, we catch and manage errors here.

## Endpoints

| Method | URL                     | Body                     |
|--------|-------------------------|--------------------------|
| POST   | /api/categories         | { "name", "description" }|
| GET    | /api/categories         | -                        |
| GET    | /api/categories/{id}    | -                        |
| PUT    | /api/categories/{id}    | { "name", "description" }|
| DELETE | /api/categories/{id}    | -                        |

## Screenshots

Screenshots are available in the `/screenshots` folder.

## How to Run

1. Clone the repository
2. Configure `application.properties` with your MySQL credentials
3. Run `FoodOrderingSystemApplication.java`
4. Access the app at `http://localhost:8080`

## Status
Assignment completed ✅