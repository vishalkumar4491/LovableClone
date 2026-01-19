package com.example.lovable_clone.dto.member;

import com.example.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long id,
        String email,
        String name,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt,
        Instant acceptedAt
) {
}
