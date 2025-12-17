package my.learn.mireaffjpractice12.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateNoteRequest {


    @Schema(
            name = "title",
            description = "Заголовок/название новой заметки",
            example = "My Note",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1,
            maxLength = 50,
            type = "String"
    )
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @Schema(
            name = "content",
            description = "Описание новой заметки",
            example = "Content",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String"
    )
    @NotBlank
    private String content;

}
