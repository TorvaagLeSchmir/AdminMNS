package com.projet.filrouge.Services;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
@Service
public class FileService {


    public MultipartFile fetchFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            byte[] fileContent = StreamUtils.copyToByteArray(url.openStream());
            return new MockMultipartFile("file", "filename", null, fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file from URL: " + fileUrl, e);
        }
    }
    }
