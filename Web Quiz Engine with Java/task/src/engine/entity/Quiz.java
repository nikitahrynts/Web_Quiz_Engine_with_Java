package engine.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> options;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> answer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Completion> completions;

    public Quiz() {
    }

    public Quiz(Long id, String title, String text, List<String> options, List<Integer> answer, User user, List<Completion> completions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.user = user;
        this.completions = completions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Completion> getCompletions() {
        return completions;
    }

    public void setCompletions(List<Completion> completions) {
        this.completions = completions;
    }
}
