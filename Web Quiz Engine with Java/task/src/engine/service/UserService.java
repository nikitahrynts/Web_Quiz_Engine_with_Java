package engine.service;

import engine.dto.UserRegistrationRequest;
import engine.entity.User;
import engine.mapper.UserMapper;
import engine.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public ResponseEntity<?> register(UserRegistrationRequest request) {
        try {
            String password = encoder.encode(request.getPassword());
            User user = mapper.convertRegistrationRequestToUser(request);
            user.setPassword(password);
            if (repository.findUserByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            repository.save(user);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
