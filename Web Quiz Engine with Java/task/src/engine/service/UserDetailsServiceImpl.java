package engine.service;

import engine.entity.User;
import engine.repository.UserRepository;
import engine.security.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Not found")
        );
        return new UserData(user);
    }
}
