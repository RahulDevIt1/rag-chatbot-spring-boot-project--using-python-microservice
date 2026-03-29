package com.rahul.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.demo.model.DocumentChunk;
import com.rahul.demo.repository.DocumentRepository;
import com.rahul.demo.service.PdfService;
import com.rahul.demo.service.PythonAiService;
import com.rahul.demo.service.SimilarityService;
import com.rahul.demo.service.TextSplitter;


@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private TextSplitter textSplitter;

    @Autowired
    private DocumentRepository documentRepository;  

    

    @Autowired
    private SimilarityService similarityService;

    

    @Autowired
    private PythonAiService pythonAiService;

    @GetMapping("/test")
    public String secureTestApi()
{
    return "Secured AI Api is working!";
}

@PostMapping(value="/upload" , consumes = "multipart/form-data")
public String uploadFile(@RequestParam("file") MultipartFile file){
    try {
            String fileName = file.getOriginalFilename();
            System.out.println("Received file: " + fileName + ", size=" + file.getSize() + ", type=" + file.getContentType());

            String text = pdfService.extractText(file);

            String path = "C:/uploads/" + fileName;

        List<String> chunks= textSplitter.splitText(text);

        for(String chunk: chunks){
            List<Double> embedding = pythonAiService.getEmbedding(chunk);
            DocumentChunk doc=documentRepository.save(new DocumentChunk(chunk, file.getOriginalFilename()));
            doc.setEmbedding(embedding);
            documentRepository.save(doc);
        }

        return "File uploaded successfully: " + fileName + " at path: " + path + "file stored in DB";
} catch (IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
            return "Upload failed: " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload failed: " + e.getMessage();
    }
}

@GetMapping("/search")
public List<String> search(@RequestParam String query){
    List<DocumentChunk> results= documentRepository.findByContentContainingIgnoreCase(query);
    return results.stream().map(DocumentChunk::getContent).toList();    
}



@GetMapping("/ask")
public String ask(@RequestParam String query) {
    return pythonAiService.askAI(query);
}



}