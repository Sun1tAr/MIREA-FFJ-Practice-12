package my.learn.mireaffjpractice12.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenRequest {

    @Schema(
            name = "refreshToken",
            description = "Refresh токен, переданный пользователю при авторизации",
            example = "xxx.yyy.zzz",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    private String refreshToken;

}
