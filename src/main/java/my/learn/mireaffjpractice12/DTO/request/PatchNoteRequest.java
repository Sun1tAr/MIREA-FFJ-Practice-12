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
            name = "Поля, которые необходимо заменить",
            description = "Передается список названий полей и их обновленных значений в формате \"ключ : значение\"",
            type = "String"
    )
    @NotNull
    private Map<String,Object> fields;

}
