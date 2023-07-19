package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.Services.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JustificatifController {

    private final FileStorageService fileStorageService;


    @Value("${app.justificatifs-dir}")
    private String justificatifsDir;

    @Value("${app.doc-dir}")
    private String docDir;

    public JustificatifController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/admin/justificatifs/{filename:.+}")
    public ResponseEntity<byte[]> serveJustificatif(@PathVariable String filename) {
        return fileStorageService.serveFileFromDirectory(justificatifsDir, filename);
    }

    @GetMapping("/admin/documents/{filename:.+}")
    public ResponseEntity<byte[]> servePhoto(@PathVariable String filename) {
        return fileStorageService.serveFileFromDirectory(docDir, filename);
    }



}
