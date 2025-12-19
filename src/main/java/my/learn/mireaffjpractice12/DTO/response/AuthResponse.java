package my.learn.mireaffjpractice12.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import my.learn.mireaffjpractice12.model.JWToken;

import java.util.List;

@Data
@Builder
public class AuthResponse {

    @Schema(
            description = "Список токенов позволяющих авторизоваться посредством Bearer Authentication и обновить список токенов"
    )
    private List<JWToken> tokens;

}
