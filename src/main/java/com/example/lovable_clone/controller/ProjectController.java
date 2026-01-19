package com.example.lovable_clone.controller;

import com.example.lovable_clone.dto.project.ProjectResponse;
import com.example.lovable_clone.dto.project.Projectrequest;
import com.example.lovable_clone.dto.project.ProjecySummaryResponse;
import com.example.lovable_clone.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjecySummaryResponse>> getMyProjects() {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.ok(projectService.getUserProjectById(id, userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody Projectrequest request) {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody Projectrequest request) {
        Long userId = 1L; // TODO: get userId from token
        return ResponseEntity.ok(projectService.updateProject(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Long userId = 1L; // TODO: get userId from token
        projectService.softDelete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
