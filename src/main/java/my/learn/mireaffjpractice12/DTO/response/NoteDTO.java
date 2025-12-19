package my.learn.mireaffjpractice12.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteDTO {

    @Schema(
            description = "Id пользователя",
            type = "long"
    )
    private Long id;

    @Schema(
            name = "title",
            description = "Заголовок/название заметки",
            example = "My Note",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1,
            maxLength = 50,
            type = "String"
    )
    private String title;

    @Schema(
            name = "content",
            description = "Описание заметки",
            example = "Content",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String"
    )
    private String content;

    @Schema(
            description = "Время создания заметки",
            example = "2025-12-20T10:42:08.041+00:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @Schema(
            description = "Время последнего изменения заметки",
            example = "2025-12-20T10:42:08.041+00:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

}
