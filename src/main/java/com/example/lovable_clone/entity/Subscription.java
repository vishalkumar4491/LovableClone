package com.example.lovable_clone.entity;

import com.example.lovable_clone.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(
        name = "subscription",
        indexes = {
                @Index(name = "idx_subscription_user", columnList = "user_id"),
                @Index(name = "idx_subscription_stripe_id", columnList = "stripe_subscription_id"),
                @Index(name = "idx_subscription_active_period", columnList = "current_period_end")
        }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    Plan plan;

    SubscriptionStatus staus;

    @Column(name = "stripe_subscription_id", nullable = false, length = 100, unique = true)
    String stripeSubscriptionId;

    @Column(name = "current_period_start", nullable = false)
    Instant currentPeriodStart;

    @Column(name = "current_period_end", nullable = false)
    Instant currentPeriodEnd;

    @Column(name = "cancel_at_period_end", nullable = false)
    Boolean cancelAtPeriodEnd = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
