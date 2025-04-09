package engine.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "completion")
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdAt;

    public Completion() {
    }

    public Completion(Long id, Quiz quiz, User user, LocalDateTime createdAt) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
