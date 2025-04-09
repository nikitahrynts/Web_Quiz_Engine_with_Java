package engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CompletionDTO {

    @JsonProperty("id")
    private Long quizId;
    @JsonProperty("completedAt")
    private LocalDateTime createdAt;

    public CompletionDTO() {
    }

    public CompletionDTO(Long quizId, LocalDateTime createdAt) {
        this.quizId = quizId;
        this.createdAt = createdAt;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
