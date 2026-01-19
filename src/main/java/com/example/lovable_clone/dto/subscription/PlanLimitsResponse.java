package com.example.lovable_clone.dto.subscription;

public record PlanLimitsResponse(
        String planName,
        int maxTokensPerDay,
        int maxProjects,
        int maxPreviews,
        boolean unlimitedAi
) {
}
