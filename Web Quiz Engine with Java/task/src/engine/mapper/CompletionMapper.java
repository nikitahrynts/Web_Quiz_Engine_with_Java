package engine.mapper;

import engine.dto.CompletionDTO;
import engine.entity.Completion;
import org.springframework.stereotype.Component;

@Component
public class CompletionMapper {

    public CompletionDTO convertToDTO(Completion completion) {
        return new CompletionDTO(completion.getQuiz().getId(), completion.getCreatedAt());
    }
}
