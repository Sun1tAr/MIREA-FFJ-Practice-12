package my.learn.mireaffjpractice12.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import my.learn.mireaffjpractice12.DTO.request.LoginUserRequest;
import my.learn.mireaffjpractice12.DTO.request.RefreshTokenRequest;
import my.learn.mireaffjpractice12.DTO.request.RegisterUserRequest;
import my.learn.mireaffjpractice12.DTO.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "API Пользовательской безопасности")
public interface AuthController {


    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Пользователь регистрируется в контексте приложения и получает токены доступа и свежести",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для регистрации пользователя",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterUserRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример валидных данных",
                                            value = "{\"email\":\"john@example.com\",\"password\":\"Pass123\"}\n"
                                    ),
                                    @ExampleObject(
                                            name = "Пример невалидных данных",
                                            value = "{\"email\":\"joexample.com\",\"password\":\"Pass123\"}"
                                    )
                            }

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно создан",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример данных авторизации",
                                                    value = "{\n" +
                                                            "    \"tokens\": [\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"ACCESS_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.377+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-18T16:01:56.377+00:00\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"REFRESH_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.450+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-31T16:01:56.450+00:00\"\n" +
                                                            "        }\n" +
                                                            "    ]\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Пользователь с таким email уже существует",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример ответа",
                                                    value = "{\n" +
                                                            "    \"message\": \"could not execute statement [ERROR: " +
                                                            "duplicate key value violates unique constraint \\\"uk46ygss" +
                                                            "fdmp410ncsrcmnythun\\\"\\n  Подробности: " +
                                                            "Key (username)=(john@example.com) already exists.] [insert " +
                                                            "into user_auths (authorities,password,user_id,username) " +
                                                            "values (?,?,?,?)]; SQL [insert into user_auths (authorities," +
                                                            "password,user_id,username) values (?,?,?,?)]; constraint [" +
                                                            "uk46ygssfdmp410ncsrcmnythun]\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Переданы невалидные данные. " +
                                    "Причины невалидности представлены в теле ответа",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Email не соответствует необходимому формату",
                                                    value = "{\n" +
                                                            "    \"message\": \"Переданы невалидные данные\",\n" +
                                                            "    \"errors\": {\n" +
                                                            "        \"email\": \"должно иметь формат адреса электронной почты\"\n" +
                                                            "    }\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/register")
    ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterUserRequest req);

    @Operation(
            summary = "Выход пользователя",
            description = "Пользователь входит в контекст безопасности приложения и получает токены доступа и свежести",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для входа пользователя",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginUserRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример валидных данных",
                                            value = "{\"email\":\"john@example.com\",\"password\":\"Pass123\"}\n"
                                    ),
                                    @ExampleObject(
                                            name = "Пример невалидных данных",
                                            value = "{\"email\":\"joexample.com\",\"password\":\"Pass123\"}"
                                    )
                            }

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно авторизован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример данных авторизации",
                                                    value = "{\n" +
                                                            "    \"tokens\": [\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"ACCESS_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.377+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-18T16:01:56.377+00:00\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"REFRESH_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.450+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-31T16:01:56.450+00:00\"\n" +
                                                            "        }\n" +
                                                            "    ]\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Неверные учетные данные",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример ответа",
                                                    value = "{\n" +
                                                            "    \"message\": \"Invalid username or password\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Переданы невалидные данные. " +
                                    "Причины невалидности представлены в теле ответа",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Email не соответствует необходимому формату",
                                                    value = "{\n" +
                                                            "    \"message\": \"Переданы невалидные данные\",\n" +
                                                            "    \"errors\": {\n" +
                                                            "        \"email\": \"должно иметь формат адреса электронной почты\"\n" +
                                                            "    }\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/login")
    ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginUserRequest req);

    @Operation( //todo
            summary = "Регистрация нового пользователя",
            description = "Пользователь регистрируется в контексте приложения и получает токены доступа и свежести",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для регистрации пользователя",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterUserRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример валидных данных",
                                            value = "{\"email\":\"john@example.com\",\"password\":\"Pass123\"}\n"
                                    ),
                                    @ExampleObject(
                                            name = "Пример невалидных данных",
                                            value = "{\"email\":\"joexample.com\",\"password\":\"Pass123\"}"
                                    )
                            }

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно создан",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример данных авторизации",
                                                    value = "{\n" +
                                                            "    \"tokens\": [\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"ACCESS_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.377+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-18T16:01:56.377+00:00\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"token\": \"xxx.yyy.zzz\",\n" +
                                                            "            \"tokenType\": \"REFRESH_BEARER\",\n" +
                                                            "            \"issuedAt\": \"2025-12-17T16:01:56.450+00:00\",\n" +
                                                            "            \"expiresAt\": \"2025-12-31T16:01:56.450+00:00\"\n" +
                                                            "        }\n" +
                                                            "    ]\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Пользователь с таким email уже существует",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример ответа",
                                                    value = "{\n" +
                                                            "    \"message\": \"could not execute statement [ERROR: " +
                                                            "duplicate key value violates unique constraint \\\"uk46ygss" +
                                                            "fdmp410ncsrcmnythun\\\"\\n  Подробности: " +
                                                            "Key (username)=(john@example.com) already exists.] [insert " +
                                                            "into user_auths (authorities,password,user_id,username) " +
                                                            "values (?,?,?,?)]; SQL [insert into user_auths (authorities," +
                                                            "password,user_id,username) values (?,?,?,?)]; constraint [" +
                                                            "uk46ygssfdmp410ncsrcmnythun]\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Переданы невалидные данные. " +
                                    "Причины невалидности представлены в теле ответа",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Email не соответствует необходимому формату",
                                                    value = "{\n" +
                                                            "    \"message\": \"Переданы невалидные данные\",\n" +
                                                            "    \"errors\": {\n" +
                                                            "        \"email\": \"должно иметь формат адреса электронной почты\"\n" +
                                                            "    }\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/logout")
    ResponseEntity<AuthResponse> logoutUser();

    @PostMapping("/refresh")
    ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest req);


}
