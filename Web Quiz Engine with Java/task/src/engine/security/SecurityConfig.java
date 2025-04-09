package engine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable).headers(cfg -> cfg.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register", "/actuator/shutdown", "/error/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/quizzes", "/api/quizzes/*/solve").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/quizzes/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/quizzes/*").authenticated()
                        .requestMatchers("/h2-console/**").authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
