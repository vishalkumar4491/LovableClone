package com.example.lovable_clone.dto.project;

import java.time.Instant;

public record ProjecySummaryResponse(Long id, String name, Instant createdAt, Instant updatedAt) {
}
