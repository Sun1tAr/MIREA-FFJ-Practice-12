package my.learn.mireaffjpractice12.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginUserRequest {

    @Schema(
            description = "Логин пользователя в формате почты",
            pattern = "^[A-Za-z0-9_.-]+@[A-Za-z0-9.-]\\.[A-Za-z]{2,}",
            example = "vasya@mail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email
    private String email;

    @Schema(
            description = "Пароль пользователя",
            example = "qwerty123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String password;

}
