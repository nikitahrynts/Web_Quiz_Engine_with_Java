package engine.service;

import engine.dto.CompletionDTO;
import engine.dto.QuizDTO;
import engine.entity.Completion;
import engine.entity.Quiz;
import engine.entity.User;
import engine.mapper.CompletionMapper;
import engine.mapper.QuizMapper;
import engine.model.Answer;
import engine.model.QuizResponse;
import engine.model.QuizSolveResponse;
import engine.repository.QuizCompletionRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import engine.security.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository repository;
    private final UserRepository userRepository;
    private final QuizMapper mapper;
    private final CompletionMapper completionMapper;
    private final QuizCompletionRepository quizCompletionRepository;

    public QuizService(QuizRepository repository, UserRepository userRepository, QuizMapper mapper, CompletionMapper completionMapper, QuizCompletionRepository quizCompletionRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.completionMapper = completionMapper;
        this.quizCompletionRepository = quizCompletionRepository;
    }

    public QuizResponse createQuiz(QuizDTO quizDTO, UserData userData) {
        Quiz quiz = mapper.convertToEntity(quizDTO);
        String userName = userData.getUsername();
        Optional<User> user = userRepository.findUserByEmail(userName);
        user.ifPresent(quiz::setUser);
        repository.save(quiz);
        return new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    public Page<QuizResponse> getAllQuizzes(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Quiz> quizzes = repository.findAll(pageable);
        return quizzes.map(mapper::convertToResponse);
    }

    public QuizResponse findQuizById(Long id) {
        QuizResponse quizResponse = null;
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isPresent()) {
            Quiz response = quiz.get();
            quizResponse = new QuizResponse(response.getId(), response.getTitle(), response.getText(), response.getOptions());
        }
        return quizResponse;
    }

    public QuizSolveResponse checkQuizAnswers(Long id, Answer answer) {
        QuizSolveResponse quizSolveResponse = null;
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isPresent()) {
            Quiz checkQuiz = quiz.get();
            if (!answer.getAnswer().stream().sorted().toList()
                    .equals(checkQuiz.getAnswer().stream().sorted().toList())) {
                quizSolveResponse = new QuizSolveResponse(false, "Wrong answer! Please, try again.");
            } else {
                quizSolveResponse = new QuizSolveResponse(true, "Congratulations, you're right!");
            }
        }
        return quizSolveResponse;
    }

    public ResponseEntity<?> deleteQuizById(Long id, UserData user) {
        Optional<Quiz> optionalQuiz = repository.findById(id);
        if (optionalQuiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = optionalQuiz.get();
        if (!quiz.getUser().getEmail().equals(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void saveUserCompletion(Long id, UserData userData) {
        Completion completion = new Completion();
        Optional<Quiz> quiz = repository.findById(id);
        Optional<User> user = userRepository.findUserByEmail(userData.getUsername());
        quiz.ifPresent(completion::setQuiz);
        user.ifPresent(completion::setUser);
        completion.setCreatedAt(LocalDateTime.now());
        quizCompletionRepository.save(completion);
    }

    public Page<CompletionDTO> getUserCompletions(Integer page, UserData user) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "createdAt");
        Page<Completion> completions = quizCompletionRepository.findAllByUser(user.getUsername(), pageable);
        return completions.map(completionMapper::convertToDTO);
    }
}
