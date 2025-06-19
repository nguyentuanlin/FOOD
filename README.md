# Food Module Application

A Spring Boot application with two modules: User Management and Food Menu Management, providing REST APIs with Swagger UI for documentation and testing.

## Features

### User Management
- User registration and management
- User profile management
- Username and email validation

### Food Menu Management
- Food categories management
- Food items management
- Search and filter food items
- Food availability management

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Validation
- H2 Database (for development)
- MySQL Support (for production)
- Swagger UI (OpenAPI)
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
```
mvn clean install
```
4. Run the application:
```
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

## API Endpoints

### User Management
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `POST /api/users` - Create a new user
- `PUT /api/users/{id}` - Update a user
- `DELETE /api/users/{id}` - Delete a user
- `GET /api/users/check-username/{username}` - Check username availability
- `GET /api/users/check-email/{email}` - Check email availability

### Category Management
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `GET /api/categories/name/{name}` - Get category by name
- `POST /api/categories` - Create a new category
- `PUT /api/categories/{id}` - Update a category
- `DELETE /api/categories/{id}` - Delete a category
- `GET /api/categories/check-name/{name}` - Check category name availability

### Food Item Management
- `GET /api/food-items` - Get all food items
- `GET /api/food-items/available` - Get all available food items
- `GET /api/food-items/{id}` - Get food item by ID
- `GET /api/food-items/category/{categoryId}` - Get food items by category
- `GET /api/food-items/search?keyword={keyword}` - Search food items
- `POST /api/food-items` - Create a new food item
- `PUT /api/food-items/{id}` - Update a food item
- `DELETE /api/food-items/{id}` - Delete a food item
- `PATCH /api/food-items/{id}/availability?available={boolean}` - Update food item availability

## Database

The application uses H2 in-memory database for development.
- H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:fooddb`
- Username: `sa`
- Password: `password`

For production, you can configure MySQL in `application.properties`. 