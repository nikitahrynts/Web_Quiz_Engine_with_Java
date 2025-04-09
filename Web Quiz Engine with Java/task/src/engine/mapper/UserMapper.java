package engine.mapper;

import engine.dto.UserRegistrationRequest;
import engine.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRegistrationRequest convertToRegistrationRequest(User user) {
        return new UserRegistrationRequest(user.getId(), user.getEmail(), user.getPassword());
    }

    public User convertRegistrationRequestToUser(UserRegistrationRequest request) {
        return new User(request.getId(), request.getEmail(), request.getPassword());
    }
}
