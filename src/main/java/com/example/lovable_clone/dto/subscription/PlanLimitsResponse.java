package com.example.lovable_clone.dto.subscription;

public record PlanLimitsResponse(
        String planName,
        Integer maxTokensPerDay,
        Integer maxProjects,
        Integer maxPreviews,
        Boolean unlimitedAi
) {
}
