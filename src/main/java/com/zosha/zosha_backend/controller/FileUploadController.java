package com.zosha.zosha_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:5176"
        }
)
public class FileUploadController {

    private final Path uploadDirectory =
            Paths.get("uploads");

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("Please select an image.");
            }

            String contentType =
                    file.getContentType();

            if (contentType == null ||
                    !contentType.startsWith("image/")) {

                return ResponseEntity
                        .badRequest()
                        .body("Only image files are allowed.");
            }

            Files.createDirectories(
                    uploadDirectory
            );

            String originalName =
                    file.getOriginalFilename();

            String extension = "";

            if (originalName != null &&
                    originalName.contains(".")) {

                extension =
                        originalName.substring(
                                originalName.lastIndexOf(".")
                        );
            }

            String fileName =
                    UUID.randomUUID()
                            + extension;

            Path filePath =
                    uploadDirectory.resolve(
                            fileName
                    );

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String imageUrl =
                    "/uploads/" + fileName;

            return ResponseEntity.ok(
                    Map.of(
                            "imageUrl",
                            imageUrl
                    )
            );

        } catch (IOException e) {

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(
                            "Unable to upload image."
                    );
        }
    }
}