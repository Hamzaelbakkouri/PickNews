package ma.youcode.candlelight.models.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserSignUpDto {
    
    @NotEmpty(message = "User name must not be empty.")
    @Size(min = 3, max = 100, message = "User name must be between 3 and 100 character.")
    private String userName;

    @NotEmpty(message = "User email must not be empty.")
    @Email(message = "User email must be a valid email.")
    private String email;

    @NotEmpty(message = "User phone number must not be empty.")
    @Size(min = 10, max = 15, message = "User phone number must be between 10 and 15 digits.")
    private String phoneNumber;

    @NotEmpty(message = "User password must not be empty.")
    @Size(min = 6, max = 30, message = "User password must be between 6 and 30 character.")
    private String password;
}
