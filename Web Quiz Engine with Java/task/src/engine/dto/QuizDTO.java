package engine.dto;

import engine.entity.Completion;
import engine.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class QuizDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotNull
    @Size(min = 2)
    private List<String> options;
    private List<Integer> answer;
    private User user;
    private List<Completion> completions;

    public QuizDTO() {
    }

    public QuizDTO(Long id, String title, String text, List<String> options, List<Integer> answer, User user, List<Completion> completions) {
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
