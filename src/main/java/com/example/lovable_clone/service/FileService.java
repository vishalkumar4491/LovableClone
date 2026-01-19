package com.example.lovable_clone.service;

import com.example.lovable_clone.dto.file.FileContentresponse;
import com.example.lovable_clone.dto.file.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long id, Long userId);

    FileContentresponse getFileContent(Long projectId, String path, Long userId);
}
