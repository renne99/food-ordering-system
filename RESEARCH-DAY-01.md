# Research Day 01: Category CRUD & Validation

### Q1. What does CRUD stand for?
CRUD stands for Create, Read, Update, and Delete. These are the four core operations required to manage persistent data within any database application.

### Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?
* **POST**: Used to create a brand-new resource. It is not idempotent (sending the same payload twice creates two separate items).
* **PUT**: Used to replace an entire existing resource. If you only send one field, the other fields are wiped out or set to defaults.
* **PATCH**: Used for partial updates. It modifies only the specific fields you send in the request body, leaving the rest untouched.
* **DELETE**: Used to permanently remove a resource from the system by its identifier.

### Q3. Give the correct HTTP status code for each:
* **a. A new category was created**: `201 Created`
* **b. A category was deleted successfully**: `204 No Content`
* **c. The id requested does not exist**: `404 Not Found`
* **d. The request body is missing a required field**: `400 Bad Request`
* **e. The user is logged in but not allowed**: `403 Forbidden`

### Q4. Difference between @RequestBody, @RequestParam, @PathVariable
* **@RequestBody**: Binds the incoming JSON payload fields directly into a Java object.
    * *Example*: `public CategoryDto create(@RequestBody CategoryDto dto)` -> Maps JSON to the DTO.
* **@RequestParam**: Extracts query parameters appended to the end of the URL string.
    * *Example*: `/api/categories?page=1` -> `public List<CategoryDto> list(@RequestParam int page)`
* **@PathVariable**: Extracts a dynamic variable directly out of the URL path structure itself.
    * *Example*: `/api/categories/5` -> `public CategoryDto getOne(@PathVariable Long id)`

### Q5. What is Jakarta Bean Validation? Explain @Valid, @NotBlank, @Size.
Jakarta Bean Validation is a standard specification used to enforce data integrity constraints on incoming data models before your code processes them.
* **@Valid**: An annotation applied to controller parameters telling Spring Boot to trigger validation checks on the object immediately upon entry.
* **@NotBlank**: Ensures a string field is neither null nor completely empty/blank spaces.
* **@Size**: Restricts a string, collection, or array's length within a designated minimum and maximum boundary.

### Q6. Why return a DTO and not the entity itself?
1. **Decoupling/Security**: It prevents sensitive database design details or internal passwords/tokens from being leaked directly to the client JSON payload.
2. **Payload Flexibility**: It allows you to tailor the API structure into formats the frontend specifically needs without polluting database table mappings.

### Q7. What is Optional<T>? Why does findById return Optional?
`Optional<T>` is a container object used to explicitly represent the fact that a value might or might not be present. Spring Data's `findById` returns an `Optional` because a user might query an identifier (like `id = 9999`) that does not exist in the database table, forcing developers to handle empty cases safely to avoid crashing with a `NullPointerException`.

---

## Self-Quiz Answers

### Q1. Why ResponseEntity instead of returning the object?
`ResponseEntity` gives full programmatic control over the entire HTTP response, allowing you to explicitly modify the status codes, headers, and the body text returned to the client rather than relying on Spring's default behavior.

### Q2. What status should a successful DELETE return? Why?
It should return `204 No Content` because the deletion was fully processed and executed, leaving no data or body required to return back to the client.

### Q3. Update only one field - PUT or PATCH? Defend your answer.
**PATCH** is correct. By REST standards, a `PUT` operation implies a full replacement of the entity state. Sending only one field via `PUT` would technically wipe out or reset the other unmentioned fields.

### Q4. What happens if you forget @Valid on the controller?
The validation constraints (like `@NotBlank` or `@Size`) will be silently ignored. The application will happily process corrupt or malformed data strings, which can later crash the service or database layers.

### Q5. Why must update/delete have {id} in the URL but create does not?
Update and delete modify *specific, existing unique records* already allocated inside the system, requiring an ID to target the exact target row. Creating a brand new record relies on the database to automatically generate a unique ID on execution.