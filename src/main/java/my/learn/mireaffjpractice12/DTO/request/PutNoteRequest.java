package my.learn.mireaffjpractice12.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutNoteRequest {


    @Schema(
            name = "Обновленный заголовок/название заметки",
            example = "My Note",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1,
            maxLength = 50
    )
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;


    @Schema(
            name = "Обновленное описание заметки",
            example = "Content",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String content;

}
