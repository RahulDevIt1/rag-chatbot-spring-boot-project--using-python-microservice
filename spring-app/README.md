# Spring Boot Service

This is the main backend service of the RAG chatbot project.  
It handles API requests, authentication, database operations, and coordinates with the Python AI service.

---

## What this service does

- Provides REST APIs for the application
- Handles user authentication using JWT
- Accepts file uploads (PDF)
- Extracts and splits text into chunks
- Stores chunks and embeddings in MongoDB
- Calls Python AI service for embeddings and responses

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security (JWT)
- MongoDB
- Maven
- Swagger (OpenAPI)

---

## How to run

### 1. Start MongoDB

Make sure MongoDB is running locally:

mongodb://localhost:27017/rag_app

---

### 2. Start Python AI Service

Go to ai-service folder and run:

python app.py

This runs on:

http://localhost:5000

---

### 3. Run Spring Boot

From this folder:

mvn spring-boot:run

The application will start on:

http://localhost:8080

---

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui.html

You can test all APIs directly from the browser.

---

## Authentication

1. Call /auth/login
2. Get JWT token from response
3. In Swagger, click "Authorize"
4. Enter token as:

Bearer <your_token>

---

## Main APIs

### Login
POST /auth/login

### Upload File
POST /ai/upload

### Search
GET /ai/search?query=...

### Ask AI
GET /ai/ask?query=...

---

## How it works

1. User uploads a PDF
2. Text is extracted and split into chunks
3. Each chunk is sent to Python service for embedding
4. Data is stored in MongoDB
5. On query:
   - Query is converted to embedding
   - Similar chunks are retrieved
   - Context is sent to AI service
   - Response is returned to user

---

## Integration with Python Service

Spring Boot communicates with the Python service using REST APIs:

- POST /embed → for embeddings
- POST /ask → for AI responses

---

## Notes

- This service does not directly call the AI model
- AI logic is delegated to Python service
- Designed to simulate real microservice architecture

---

## Future Improvements

- Replace manual similarity with vector database
- Add caching for faster responses
- Add frontend UI
- Improve ranking and accuracy

---

## Author

Rahul Dev