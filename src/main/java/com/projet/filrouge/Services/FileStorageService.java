package com.projet.filrouge.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final ResourceLoader resourceLoader;

    private final Path fileStorageLocationJustificatif = Paths.get("/Users/BlackCalx/Desktop/DEV/AdminMNS_justificatifs");
    private final Path getFileStorageLocationDocuments = Paths.get("/Users/BlackCalx/Desktop/DEV/AdminMNS_documents");

    public FileStorageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String storeFileJustificatif(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            Path targetLocation = this.fileStorageLocationJustificatif.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation);

            return targetLocation.toUri().toString();

        } catch (Exception ex) {
            throw new RuntimeException("Failed to store file", ex);
        }
    }

    public String storeFileDocument(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            Path targetLocation = this.getFileStorageLocationDocuments.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation);

            return targetLocation.toUri().toString();

        } catch (Exception ex) {
            throw new RuntimeException("Failed to store file", ex);
        }
    }
    public ResponseEntity<byte[]> serveFileFromDirectory(String directory, String filename) {
        try {
            Resource fileResource = resourceLoader.getResource("file:" + directory + "/" + filename);
            if (fileResource.exists() || fileResource.isReadable()) {
                String mimeType = Files.probeContentType(Paths.get(fileResource.getURI()));
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileResource.getFilename() + "\"");
                headers.add(HttpHeaders.CONTENT_TYPE, mimeType != null ? mimeType : "application/octet-stream");
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
