package com.example.lovable_clone.entity;

import com.example.lovable_clone.enums.PreviewStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * Preview entity represents a temporary preview environment
 * created for a project to allow users to preview AI-generated
 * applications before finalizing or deploying them.
 */
@Entity
@Table(
        name = "preview",
        indexes = {
                @Index(name = "idx_preview_project", columnList = "project_id"),
                @Index(name = "idx_preview_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    @Column(nullable = false, length = 100)
    String namespace;

    @Column(name = "pod_name", nullable = false, length = 150)
    String podName;

    @Column(name = "preview_url", nullable = false, length = 500)
    String previewUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    PreviewStatus status;

    @Column(name = "started_at")
    Instant startedAt;

    @Column(name = "terminated_at")
    Instant terminatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}