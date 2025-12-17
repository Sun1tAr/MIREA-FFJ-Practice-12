package my.learn.mireaffjpractice12.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchNoteRequest {

    @Schema(
            name = "fields",
            description = "Поля, которые необходимо заменить. " +
                    "Передается список названий полей и их обновленных значений в формате \"ключ : значение\"",
            type = "Map<String, Object>"
    )
    @NotNull
    private Map<String,Object> fields;

}
