package com.example.lovable_clone.entity;

import com.example.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(
        name = "project_member",
        indexes = {
                @Index(name = "idx_project_member_project", columnList = "project_id"),
                @Index(name = "idx_project_member_user", columnList = "user_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("projectId")
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_role", nullable = false, length = 30)
    ProjectRole projectRole;

    @Column(name = "invited_at", nullable = false, updatable = false)
    Instant invitedAt;

    @Column(name = "accepted_at")
    Instant acceptedAt;

    @PrePersist
    protected void onInvite() {
        this.invitedAt = Instant.now();
    }
}
