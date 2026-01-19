package com.example.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(
        name = "plan",
        indexes = {
                @Index(name = "idx_plan_name", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)    // It will automatically add AccessLevel type like Private in these case in front of each field
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    String name;  // FREE, PRO etc

    Integer maxProjects;

    Integer maxTokensPerDay;

    Integer maxPreviews;    // Max Previews per plan allowed

    Boolean unlimitedAi;     // ignore MaxTokensPerDay f true

    Boolean active;
}
