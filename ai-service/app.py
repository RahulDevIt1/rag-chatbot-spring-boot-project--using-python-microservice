from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

OLLAMA_URL = "http://localhost:11434/api/generate"

@app.route("/ask", methods=["POST"])
def ask():
    data = request.json
    prompt = data.get("prompt")

    response = requests.post(OLLAMA_URL, json={
        "model": "phi3",
        "prompt": prompt,
        "stream": False
    })

    return jsonify(response.json())

@app.route("/embed", methods=["POST"])
def embed():
    data = request.json
    text = data.get("text")

    response = requests.post("http://localhost:11434/api/embeddings", json={
        "model": "nomic-embed-text",
        "prompt": text
    })

    return jsonify(response.json())

if __name__ == "__main__":
    app.run(port=5000)