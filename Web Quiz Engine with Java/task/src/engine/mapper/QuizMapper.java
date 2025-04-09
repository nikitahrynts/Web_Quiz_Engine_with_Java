package engine.mapper;

import engine.dto.QuizDTO;
import engine.entity.Quiz;
import engine.model.QuizResponse;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizResponse convertToResponse(Quiz quiz) {
        return new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    public Quiz convertToEntity(QuizDTO quizDTO) {
        return new Quiz(quizDTO.getId(), quizDTO.getTitle(), quizDTO.getText(), quizDTO.getOptions(), quizDTO.getAnswer(), quizDTO.getUser(), quizDTO.getCompletions());
    }
}
