package engine.controller;

import engine.dto.CompletionDTO;
import engine.dto.QuizDTO;
import engine.model.Answer;
import engine.model.QuizResponse;
import engine.model.QuizSolveResponse;
import engine.security.UserData;
import engine.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quizzes")
    public QuizResponse createQuiz(@RequestBody @Valid QuizDTO quizDTO,
                                   @AuthenticationPrincipal UserData user) {
        return quizService.createQuiz(quizDTO, user);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Page<QuizResponse>> getAllQuizzes(@RequestParam Integer page) {
        Page<QuizResponse> response = quizService.getAllQuizzes(page);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        QuizResponse quizResponse = quizService.findQuizById(id);
        if (quizResponse != null) {
            return ResponseEntity.ok(quizResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<QuizSolveResponse> checkAnswer(@PathVariable Long id,
                                                         @RequestBody Answer answer,
                                                         @AuthenticationPrincipal UserData userData) {
        QuizSolveResponse quizSolveResponse = quizService.checkQuizAnswers(id, answer);
        if (quizSolveResponse != null) {
            if (quizSolveResponse.isSuccess()) {
                quizService.saveUserCompletion(id, userData);
            }
            return ResponseEntity.ok(quizSolveResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id,
                                        @AuthenticationPrincipal UserData user) {
        return quizService.deleteQuizById(id, user);
    }

    @GetMapping("/quizzes/completed")
    public Page<CompletionDTO> getUserCompletions(@RequestParam Integer page,
                                                  @AuthenticationPrincipal UserData user) {
        return quizService.getUserCompletions(page, user);
    }
}
