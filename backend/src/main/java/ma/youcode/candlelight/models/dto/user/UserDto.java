package ma.youcode.candlelight.models.dto.user;

import java.time.LocalDate;

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

public class UserDto {
    
    private Long id;
    private String userName;
    private String email;
    private String phoneNumber;
    private LocalDate joinedAt;
}
