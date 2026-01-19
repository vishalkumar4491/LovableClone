package com.example.lovable_clone.entity;

import com.example.lovable_clone.enums.MessageRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * ChatMessage represents a single message exchanged in a ChatSession.
 * It can be a user message, AI response, or a system/tool-generated message.
 * This entity is used to store conversational history along with
 * AI metadata such as token usage and tool calls.
 */
@Entity
@Table(
        name = "chat_message",
        indexes = {
                @Index(name = "idx_chat_message_session", columnList = "chat_session_id"),
                @Index(name = "idx_chat_message_role", columnList = "role")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_session_id", nullable = false)
    ChatSession chatSession;

    @Column(nullable = false, columnDefinition = "TEXT")
    String content;   // actual message content (user input or AI output)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    MessageRole role; // USER, ASSISTANT, SYSTEM, TOOL

    @Column(name = "tool_calls", columnDefinition = "TEXT")
    String toolCalls; // JSON array representing tools/functions invoked by AI

    @Column(name = "tokens_used")
    Integer tokensUsed; // number of LLM tokens consumed for this message

    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}
