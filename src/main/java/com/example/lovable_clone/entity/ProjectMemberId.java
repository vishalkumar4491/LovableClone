package com.example.lovable_clone.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProjectMemberId implements Serializable {

    private static final long serialVersionUID = 1L;

    Long projectId;

    Long userId;
}
