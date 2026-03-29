# AI Service (app.py)

This is a lightweight Python service built using Flask.  
It handles all AI-related operations for the RAG chatbot project and works as a bridge between the Spring Boot backend and Ollama.

---

## What this service does

- Receives requests from the Spring Boot application
- Sends prompts to Ollama for response generation
- Generates embeddings for text chunks
- Returns results back to the backend

---

## Endpoints

### 1. Ask AI

POST /ask

Request:
{
  "prompt": "your question"
}

Response:
{
  "response": "generated answer"
}

---

### 2. Generate Embedding

POST /embed

Request:
{
  "text": "input text"
}

Response:
{
  "embedding": [vector values]
}

---

## How to run

Make sure Python is installed on your system.

Install required dependencies:

pip install flask requests

Run the service:

python app.py

The service will start on:

http://localhost:5000

---

## Requirements

- Python 3+
- Ollama installed and running locally

Start Ollama:

ollama serve

Pull required models:

ollama pull phi3
ollama pull nomic-embed-text

---

## Integration with Spring Boot

This service is not standalone. It is called by the Spring Boot application.

Flow:

Spring Boot → Python API → Ollama → Response → Spring Boot

---

## Notes

- This service is intentionally kept simple
- AI logic is separated from backend for flexibility
- Models can be changed without modifying Spring Boot
- Useful for scaling AI independently

---

## Example Requests

### Ask AI

curl -X POST http://localhost:5000/ask \
-H "Content-Type: application/json" \
-d "{\"prompt\":\"What is Java?\"}"

---

### Generate Embedding

curl -X POST http://localhost:5000/embed \
-H "Content-Type: application/json" \
-d "{\"text\":\"Java is a programming language\"}"