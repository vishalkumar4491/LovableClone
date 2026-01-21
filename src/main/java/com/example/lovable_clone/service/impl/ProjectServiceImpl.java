package com.example.lovable_clone.service.impl;

import com.example.lovable_clone.dto.project.ProjectResponse;
import com.example.lovable_clone.dto.project.Projectrequest;
import com.example.lovable_clone.dto.project.ProjecySummaryResponse;
import com.example.lovable_clone.entity.Project;
import com.example.lovable_clone.entity.User;
import com.example.lovable_clone.mapper.ProjectMapper;
import com.example.lovable_clone.repository.ProjectRepository;
import com.example.lovable_clone.repository.UserRepository;
import com.example.lovable_clone.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    @Override
    public List<ProjecySummaryResponse> getUserProjects(Long userId) {
        return projectMapper.toProjectSummaryResponseList(projectRepository.findAllAccessibleByUser(userId));
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
//        User owner = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        Project project = getAccessibleProjectById(id, userId);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(Projectrequest request, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, Projectrequest request, Long userId) {
//        User owner = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of the project");
        }
        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);

    }

    @Override
    public void softDelete(Long id, Long userId) {
//        User owner = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of the project");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }



    /// Internal Methods ///

    private Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
