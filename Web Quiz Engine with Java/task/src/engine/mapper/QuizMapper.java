package engine.mapper;

import engine.dto.QuizDTO;
import engine.entity.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDTO convertToDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer(), quiz.getUser(), quiz.getCompletions());
    }

    public Quiz convertToEntity(QuizDTO quizDTO) {
        return new Quiz(quizDTO.getId(), quizDTO.getTitle(), quizDTO.getText(), quizDTO.getOptions(), quizDTO.getAnswer(), quizDTO.getUser(), quizDTO.getCompletions());
    }
}
