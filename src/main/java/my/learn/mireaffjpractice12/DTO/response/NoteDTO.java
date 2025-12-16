package my.learn.mireaffjpractice12.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteDTO {

    private Long id;
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

}
