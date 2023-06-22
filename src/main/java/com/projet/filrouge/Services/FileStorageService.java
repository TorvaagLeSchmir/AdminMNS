package com.projet.filrouge.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path fileStorageLocationJustificatif = Paths.get("/Users/BlackCalx/Desktop/DEV/AdminMNS_justificatifs");
    private final Path getFileStorageLocationDocuments = Paths.get("/Users/BlackCalx/Desktop/DEV/AdminMNS_documents");
    public String storeFileJustificatif(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            // Copier le fichier vers le dossier de stockage
            Path targetLocation = this.fileStorageLocationJustificatif.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation);

            // Retourner le lien vers le fichier
            return targetLocation.toUri().toString();

        } catch (Exception ex) {
            // Gérer les exceptions
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

            // Copier le fichier vers le dossier de stockage
            Path targetLocation = this.getFileStorageLocationDocuments.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation);

            // Retourner le lien vers le fichier
            return targetLocation.toUri().toString();

        } catch (Exception ex) {
            // Gérer les exceptions
            throw new RuntimeException("Failed to store file", ex);
        }
    }
}
