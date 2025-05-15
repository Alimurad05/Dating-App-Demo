package az.turing.tinderapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder

public class UserDto {
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

}

