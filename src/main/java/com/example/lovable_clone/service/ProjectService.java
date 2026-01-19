package com.example.lovable_clone.service;

import com.example.lovable_clone.dto.project.ProjectResponse;
import com.example.lovable_clone.dto.project.Projectrequest;
import com.example.lovable_clone.dto.project.ProjecySummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {
    List<ProjecySummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(Projectrequest request, Long userId);

    ProjectResponse updateProject(Long id, Projectrequest request, Long userId);

    void softDelete(Long id, Long userId);
}
