package com.projet.filrouge.Contrôleurs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JustificatifController {

    @Value("${app.justificatifs-dir}")
    private String justificatifsDir;

    private final ResourceLoader resourceLoader;

    public JustificatifController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/admin/justificatifs/{filename:.+}")
    public ResponseEntity<byte[]> serveJustificatif(@PathVariable String filename) {
        try {
            Resource fileResource = resourceLoader.getResource("file:" + justificatifsDir + "/" + filename);
            if (fileResource.exists() || fileResource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileResource.getFilename() + "\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
                byte[] fileContent = StreamUtils.copyToByteArray(fileResource.getInputStream());
                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
            } else {
                throw new RuntimeException("Could not read file " + filename);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}