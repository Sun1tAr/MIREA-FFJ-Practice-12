package my.learn.mireaffjpractice12.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

public interface HealthController {

    @Operation(
            summary = "Проверка жизни API",
            description = "Предназначен для определения работоспособности API",
            responses = {
                    @ApiResponse(
                             responseCode = "200",
                             description = "API готово к работе",
                             content = @Content(
                                     mediaType = "application/json",
                                     examples = @ExampleObject(
                                             name = "Успешный ответ",
                                             value = "{\n" +
                                                     "    \"status\": \"ok\"\n" +
                                                     "}"
                                     )
                             )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @GetMapping("/health")
    default ResponseEntity<?> health() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
        return ResponseEntity.ok().body(map);
    }

}
