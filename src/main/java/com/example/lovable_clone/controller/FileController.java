package com.example.lovable_clone.controller;

import com.example.lovable_clone.dto.file.FileContentresponse;
import com.example.lovable_clone.dto.file.FileNode;
import com.example.lovable_clone.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{id}")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/files")
    public ResponseEntity<List<FileNode>> getFileTree(@PathVariable Long projectId) {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.ok(fileService.getFileTree(projectId, userId));
    }

    @GetMapping("/files/{*path}")
    public ResponseEntity<FileContentresponse> getFile(@PathVariable Long projectId, @PathVariable String path) {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }

}
