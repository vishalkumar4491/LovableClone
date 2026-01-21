package com.example.lovable_clone.service.impl;

import com.example.lovable_clone.dto.file.FileContentresponse;
import com.example.lovable_clone.dto.file.FileNode;
import com.example.lovable_clone.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long id, Long userId) {
        return List.of();
    }

    @Override
    public FileContentresponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
