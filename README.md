# RAG Chatbot – Spring Boot + Python Microservices

This project is a simple implementation of a Retrieval-Augmented Generation (RAG) chatbot using a microservice approach.

It is split into two parts:
- Spring Boot → handles APIs, authentication (JWT), MongoDB
- Python (Flask) → handles AI tasks (embeddings + LLM via Ollama)

---

## 📦 Project Structure

rag-microservices/
├── spring-app/      → main backend (Java, Spring Boot)
├── ai-service/      → AI service (Python, Flask)

---

## ⚙️ Tech Stack

- Java 17, Spring Boot
- Spring Security + JWT
- MongoDB
- Python (Flask)
- Ollama (local LLM)
- Swagger (API testing)

---

## 🚀 How to Run

### 1. Start Ollama
Make sure Ollama is running: