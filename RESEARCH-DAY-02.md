================================================================================
RESEARCH DAY 02: JAVA CONCEPTS
================================================================================

TABLE OF CONTENTS
================================================================================
1. CONCEPTS TO RESEARCH (Q1-Q7)
2. SELF-QUIZ (Q1-Q4)
3. QUICK REFERENCE


================================================================================
CONCEPTS TO RESEARCH
================================================================================

---
Q1: WHAT IS A JAVA GENERIC TYPE? WHY IS <T> USEFUL?
---

Generics let you write code once and use it with many types, while keeping
type safety.

Real-world analogy:
Think of <T> like a container label that says "any type can go here,
but stick to one."

Example - Without generics (UNSAFE):
List list = new ArrayList();
list.add("Hello");
list.add(123);  // Oops! Mixed types
String value = (String) list.get(1);  // ClassCastException!

Example - With generics (SAFE):
List<String> strings = new ArrayList<>();
strings.add("Hello");
// strings.add(123);  // Won't compile! Type-safe
String value = strings.get(0);  // No casting needed

Why it's useful:
✓ Compile-time safety - Catch errors before running
✓ No casting needed - Code is cleaner
✓ Reusable logic - Write once for all types


---
Q2: WHAT DOES LOMBOK @Builder GENERATE BEHIND THE SCENES?
---

@Builder auto-generates a fluent builder class so you can create objects
step-by-step instead of writing constructors.

What Lombok generates:
@Builder
public class User {
private String name;
private int age;
private String email;
}

// Lombok creates this automatically:
User user = User.builder()
.name("Alice")
.age(25)
.email("alice@example.com")
.build();

// Instead of:
User user = new User("Alice", 25, "alice@example.com");

Why it's helpful:
✓ Readable - You see what each value is for
✓ Optional fields - Skip fields you don't need
✓ Less code - No need to write constructors yourself


---
Q3: WHAT IS THE BUILDER DESIGN PATTERN? WHEN TO USE IT?
---
Builder is a way to construct complex objects step-by-step instead of
jamming everything into one constructor.

Problem it solves:
// Bad: Too many constructors!
User(String name) { }
User(String name, int age) { }
User(String name, int age, String email) { }
User(String name, int age, String email, boolean active) { }
// What if you want just name + email?

Builder solution:
User user = new User.Builder()
.name("Alice")
.email("alice@example.com")
.build();

When to use it:
✓ Objects with many fields
✓ Optional parameters matter
✓ You want readable, maintainable code
✓ Order of parameters shouldn't matter


---
Q4: WHAT IS LocalDateTime? HOW IS IT DIFFERENT FROM Date?
---

LocalDateTime is the modern, human-friendly way to handle dates and times
in Java. Date is the old way that had problems.

Key Differences:

FEATURE              | Date            | LocalDateTime
---------------------|-----------------|------------------
Timezone-aware       | ✗ Confusing     | ✓ You control it
Mutable              | ✗ Can change    | ✓ Immutable (safer)
Easy to use          | ✗ Clunky        | ✓ Clear methods
Parsing dates        | ✗ Not thread-safe | ✓ Thread-safe

Real example:
// Old way (2010s)
Date now = new Date();  // What timezone? Confusing!

// Modern way (2015+)
LocalDateTime now = LocalDateTime.now();
LocalDate today = LocalDate.now();
LocalTime bedtime = LocalTime.of(22, 30);  // 10:30 PM

Use LocalDateTime for:
✓ Working with dates in your application
✓ Storing dates without timezone
✓ Clear, readable date logic


---
Q5: WHY DOES CONSISTENT RESPONSE FORMAT MATTER TO FRONTEND DEVELOPERS?
---
Frontends are like restaurant customers - they expect consistent,
predictable orders every time, so they can write code that works reliably.

What happens WITHOUT consistency:

// API Response 1: User endpoint
{
"status": 200,
"user": { "id": 1, "name": "Alice" }
}

// API Response 2: Product endpoint
{
"success": true,
"data": { "id": 100, "price": 50 }
}

// API Response 3: Order endpoint
{
"code": 200,
"message": "Success",
"orders": []
}

// Frontend cries: Which field has status? success? code?

With CONSISTENT format:

{
"status": 200,
"message": "Success",
"data": { /* actual content */ },
"timestamp": "2024-01-15T10:30:00Z"
}

// Frontend can write ONE handler for ALL responses
response.status  // Always there
response.message // Always there
response.data    // Always there

Why it matters:
✓ Faster frontend development - Less guessing
✓ Fewer bugs - Predictable structure
✓ Better error handling - Same error format everywhere


---
Q6: WHAT DOES @JsonInclude(JsonInclude.Include.NON_NULL) DO?
---

It tells Jackson (JSON library) to skip fields that are null when
converting objects to JSON.

Example:

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
private String name;
private String email;
private String phone;  // might be null
}

// If phone is null:
User user = new User("Alice", "alice@example.com", null);

// JSON output (phone SKIPPED):
{
"name": "Alice",
"email": "alice@example.com"
}

// Without @JsonInclude:
{
"name": "Alice",
"email": "alice@example.com",
"phone": null  // clutters response
}

Why use it:
✓ Cleaner responses - No null fields to confuse frontend
✓ Smaller JSON - Less data to send
✓ Better UX - Frontend doesn't need null checks


---
Q7: WHAT IS A STATIC FACTORY METHOD? WHY USE Response.success(...)
INSTEAD OF new Response<>()?
---

A static factory method is a named method on the class itself that
creates and returns instances. It's clearer than calling new.

Compare:

// Factory method (CLEAR INTENT)
Response<User> response = Response.success(user);
Response<User> response = Response.error("User not found", 404);

// vs. Constructor (UNCLEAR)
Response<User> response = new Response<>(user, 200, "Success",
null, true, false);
Response<User> response = new Response<>(null, 404, "User not found",
new Exception(), false, true);
// What do all these params mean?

Factory method implementation:

public class Response<T> {
private T data;
private int status;
private String message;
private LocalDateTime timestamp;

      // Factory methods (static)
      public static <T> Response<T> success(T data) {
          return new Response<>(data, 200, "Success", 
                               LocalDateTime.now());
      }
      
      public static <T> Response<T> error(String message, int status) {
          return new Response<>(null, status, message, 
                               LocalDateTime.now());
      }
}

Why it's better:
✓ Self-documenting - Name explains what it does
✓ Less boilerplate - No need to pass all params
✓ Flexibility - Can return subclasses if needed
✓ Consistent - Everyone uses the same factory


================================================================================
SELF-QUIZ
================================================================================

---
Q1: WHY USE GENERIC <T> INSTEAD OF Object FOR DATA FIELD?
---

With Object, you lose type safety and need casting:

// Bad: Using Object
Response response = new Response();
response.setData(user);  // Could be any type!
User user = (User) response.getData();  // Must cast, error-prone

// Good: Using <T>
Response<User> response = new Response<>();
response.setData(user);
User user = response.getData();  // No cast needed, type-safe ✓

Generics prevent mistakes at compile time; Object requires risky casting.


---
Q2: DIFFERENCE BETWEEN Response<T> AND ResponseEntity<T>?
CAN YOU HAVE BOTH AT ONCE?
---


FEATURE          | Response<T>      | ResponseEntity<T>
-----------------|------------------|-------------------
Type             | Your custom      | Spring's built-in
Controls         | Data, msg, status| Also HTTP headers
Used in          | Service layer    | Controller layer
Complexity       | Simple & light   | More powerful

Can you have both?

YES! Use Response in service, wrap it in ResponseEntity in controller

@RestController
public class UserController {

      @GetMapping("/users/{id}")
      public ResponseEntity<Response<User>> getUser(@PathVariable int id) {
          User user = userService.findById(id);
          Response<User> response = Response.success(user);
          
          return ResponseEntity.ok(response);  // Wrap it!
      }
}

When to use each:
✓ Response<T> → Service/business layer
✓ ResponseEntity<T> → Controller/API layer


---
Q3: IF A REQUEST FAILS, WHAT STATUSCODE DOES Response HOLD?
---

The Response object holds whatever status code you give it (400, 404, 500,
etc.). It's not automatic.

// Success case
Response.success(user);  // status = 200

// Failure case (you choose the code)
if (user == null) {
return Response.error("User not found", 404);  // status = 404
}

if (!hasPermission(user)) {
return Response.error("Unauthorized", 403);  // status = 403
}

Common status codes:
200 - Success ✓
400 - Bad request (client error)
404 - Not found
403 - Forbidden (no permission)
500 - Server error


---
Q4: WHY ADD A TIMESTAMP?
---

Timestamps help track when the response was created, useful for:

{
"status": 200,
"message": "Success",
"data": { "id": 1, "name": "Alice" },
"timestamp": "2024-01-15T10:30:45Z"  // When was this response made?
}

Why it matters:
✓ Debugging - Know when an error happened
✓ Caching - Frontend can check if data is fresh
✓ Audit trails - Track server activity
✓ Detect stale data - Is this response from 5 minutes ago?

Simple analogy:
It's like a receipt at a store - shows you exactly when the transaction
happened.

