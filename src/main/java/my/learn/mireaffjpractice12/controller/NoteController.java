package my.learn.mireaffjpractice12.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import my.learn.mireaffjpractice12.DTO.request.CreateNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PatchNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PutNoteRequest;
import my.learn.mireaffjpractice12.DTO.response.NoteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "API Заметок",
        description = "Является основным контроллером данного сервиса и предназначен для управления заметками"
)
public interface NoteController extends HealthController {


    @Operation(
            summary = "Создание новой заметки",
            description = "Новая заметка создается и добавляется в базу данных, по умолчанию она не привязана " +
                    "к конкретному пользователю",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "В теле запроса передаются данные создаваемой заметки",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateNoteRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример тела заметки",
                                            value = "{\n" +
                                                    "    \"title\":\"My Note\",\n" +
                                                    "    \"content\":\"Content\"\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Заметка успешно создана. В теле передается созданная заметка",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример возвращаемой заметки",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"title\": \"My First Note\",\n" +
                                                            "  \"content\": \"This is my first note content\",\n" +
                                                            "  \"createdAt\": \"2025-12-14T20:15:00Z\",\n" +
                                                            "  \"updatedAt\": \"2025-12-14T20:15:00Z\"\n" +
                                                            "}\n"

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
                                                    name = "Слишком длинный \"title\"",
                                                    value = "{\n" +
                                                            "  \"message\": \"Validation failed\",\n" +
                                                            "  \"errors\": {\n" +
                                                            "    \"title\": \"Размер должен находиться в диапазоне от 1 до 50\"\n" +
                                                            "  }\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @PostMapping
    ResponseEntity<NoteDTO> addNote(@RequestBody @Valid CreateNoteRequest createNoteRequest);

    @Operation(
            summary = "Частичное обновление заметки",
            description = "В заметке с указанным Id заменяются значения указанных полей на переданные",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "В теле запроса передаются поля изменяемой заметки",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PatchNoteRequest.class),
                        examples = {
                                @ExampleObject(
                                        name = "Пример тела заметки",
                                        value = "{\n" +
                                                "    \"fields\":\n" +
                                                "    {\n" +
                                                "        \"title\":\"Updated\"\n" +
                                                "    }\n" +
                                                "}"
                                )
                        }
                )
            ),
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            description = "Id изменяемой заметки передается в path запроса"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заметка успешно изменена. В теле передается измененная заметка",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример возвращаемой заметки",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"title\": \"Updated\",\n" +
                                                            "  \"content\": \"This is my first note content\",\n" +
                                                            "  \"createdAt\": \"2025-12-14T20:15:00Z\",\n" +
                                                            "  \"updatedAt\": \"2025-12-14T20:18:00Z\"\n" +
                                                            "}\n"

                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Заметка не найдена. В теле передается сообщение ошибке",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Заметка с указанным Id не найдена",
                                                    value = "{\n" +
                                                            "    \"message\": \"Note with id -1 not found\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @PatchMapping("/{id}")
    ResponseEntity<NoteDTO> patchNote(@RequestBody @Valid PatchNoteRequest patchNoteRequest, @PathVariable(name = "id") Long id);

    @Operation(
            summary = "Полное изменение заметки",
            description = "Значения всех полей заметки заменяются на переданные",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "В теле запроса передаются данные изменяемой заметки",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PutNoteRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример тела заметки",
                                            value = "{\n" +
                                                    "    \"title\":\"Updated\",\n" +
                                                    "    \"content\":\"Updated\"\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            description = "Id изменяемой заметки передается в path запроса"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заметка успешно изменена. В теле передается измененная заметка",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример возвращаемой заметки",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"title\": \"Updated\",\n" +
                                                            "  \"content\": \"Updated\",\n" +
                                                            "  \"createdAt\": \"2025-12-14T20:15:00Z\",\n" +
                                                            "  \"updatedAt\": \"2025-12-14T20:19:00Z\"\n" +
                                                            "}\n"

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
                                                    name = "Слишком длинный \"title\"",
                                                    value = "{\n" +
                                                            "  \"message\": \"Validation failed\",\n" +
                                                            "  \"errors\": {\n" +
                                                            "    \"title\": \"Размер должен находиться в диапазоне от 1 до 50\"\n" +
                                                            "  }\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<NoteDTO> putNote(@RequestBody @Valid PutNoteRequest putNoteRequest, @PathVariable(name = "id") Long id);

    @Operation(
            summary = "Удаление заметки",
            description = "Заметка полностью удаляется из контекста приложения",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            description = "Id заметки передается в path запроса"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заметка успешно удалена. Тело ответа отсутствует",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Пример возвращаемой заметки",
                                                    value = """
                                                            {
                                                              "id": 1,
                                                              "title": "Updated",
                                                              "content": "This is my first note content",
                                                              "createdAt": "2025-12-14T20:15:00Z",
                                                              "updatedAt": "2025-12-14T20:18:00Z"
                                                            }
                                                            """

                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен"

                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteNote(@PathVariable(name = "id") Long id);

    @Operation(
            summary = "Все заметки контекста",
            description = "Получение всех заметок, находящихся в контексте приложения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Актуальный список заметок",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Непустой список заметок",
                                                    value = "[\n" +
                                                            "    {\n" +
                                                            "        \"id\": 9,\n" +
                                                            "        \"title\": \"Example 1\",\n" +
                                                            "        \"content\": \"Example 1\",\n" +
                                                            "        \"createdAt\": \"2025-12-17T19:02:41.173301\",\n" +
                                                            "        \"updatedAt\": \"2025-12-17T19:02:41.173301\"\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 10,\n" +
                                                            "        \"title\": \"Example 2\",\n" +
                                                            "        \"content\": \"Example 2\",\n" +
                                                            "        \"createdAt\": \"2025-12-17T19:02:47.687745\",\n" +
                                                            "        \"updatedAt\": \"2025-12-17T19:02:47.687745\"\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 11,\n" +
                                                            "        \"title\": \"Example 3\",\n" +
                                                            "        \"content\": \"Example 3\",\n" +
                                                            "        \"createdAt\": \"2025-12-17T19:02:54.783692\",\n" +
                                                            "        \"updatedAt\": \"2025-12-17T19:02:54.783692\"\n" +
                                                            "    }\n" +
                                                            "]"
                                            ),
                                            @ExampleObject(
                                                    name = "Пустой список заметок",
                                                    value = "[]"
                                            )
                                    },
                                    schema = @Schema(implementation = NoteDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @GetMapping
    ResponseEntity<List<NoteDTO>> getAllNotes();

    @Operation(
            summary = "Получение заметки по Id",
            description = "Получение информации о существующей заметке по ее Id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Id запрашиваемой заметки",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заметка успешно найдена",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Найденная заметка",
                                                    value = "{\n" +
                                                            "    \"id\": 9,\n" +
                                                            "    \"title\": \"Example 1\",\n" +
                                                            "    \"content\": \"Example 1\",\n" +
                                                            "    \"createdAt\": \"2025-12-17T19:02:41.173301\",\n" +
                                                            "    \"updatedAt\": \"2025-12-17T19:02:41.173301\"\n" +
                                                            "}"
                                            )
                                    },
                                    schema = @Schema(implementation = NoteDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Заметка не найдена. В теле сообщение об ошибке",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Сообщение об ошибке",
                                                    value = "{\n" +
                                                            "    \"message\": \"Note with id 9 not found\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden - Доступ к ресурсу запрещен",
                            content = @Content()

                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<NoteDTO> getNoteById(@PathVariable(name = "id") Long id);















}
