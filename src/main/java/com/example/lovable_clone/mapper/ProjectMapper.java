package com.example.lovable_clone.mapper;

import com.example.lovable_clone.dto.project.ProjectResponse;
import com.example.lovable_clone.dto.project.ProjecySummaryResponse;
import com.example.lovable_clone.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectResponse toProjectResponse(Project project);

    ProjecySummaryResponse toProjectSummaryResponse(Project project);

    List<ProjecySummaryResponse> toProjectSummaryResponseList(List<Project> projects);
}
