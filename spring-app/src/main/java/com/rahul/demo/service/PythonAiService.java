package com.rahul.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonAiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String PYTHON_URL = "http://localhost:5000";

    public String askAI(String prompt) {
        Map<String, String> body = new HashMap<>();
        body.put("prompt", prompt);

        Map response = restTemplate.postForObject(
                PYTHON_URL + "/ask",
                body,
                Map.class
        );

        return (String) response.get("response");
    }

    public List<Double> getEmbedding(String text) {
        Map<String, String> body = new HashMap<>();
        body.put("text", text);

        Map response = restTemplate.postForObject(
                PYTHON_URL + "/embed",
                body,
                Map.class
        );

        return (List<Double>) response.get("embedding");
    }
}