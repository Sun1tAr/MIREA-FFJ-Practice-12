package my.learn.mireaffjpractice12.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterUserRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

}
