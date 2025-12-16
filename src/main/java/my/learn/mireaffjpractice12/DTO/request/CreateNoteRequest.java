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



    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    private String content;

}
