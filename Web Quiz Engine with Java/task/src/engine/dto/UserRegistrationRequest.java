package engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationRequest {

    private Long id;
    @NotBlank
    @Email(regexp = "\\w+@\\w+\\.\\w+")
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
