package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/picture")
public class UploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy,
            @RequestParam("uploadDate") LocalDate uploadDate) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a file");
        }

        // Check file size
        if (file.getSize() > 500 * 1024) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds limit");
        }

        // Check file type
        String fileType = file.getContentType();
        if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only jpg/png files are supported");
        }

        try {
            // Generate unique file name
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String filePath = Paths.get(UPLOAD_DIR, fileName).toString();

            // Create directory if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Save file
            file.transferTo(new File(filePath));

            // You can save other metadata like uploadedBy and uploadDate to a database
            // For demo purpose, just log them
            System.out.println("Uploaded by: " + uploadedBy);
            System.out.println("Upload date: " + uploadDate);

            return ResponseEntity.ok("File uploaded successfully: " + filePath);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}
