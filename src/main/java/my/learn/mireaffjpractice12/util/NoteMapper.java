package my.learn.mireaffjpractice12.util;

import my.learn.mireaffjpractice12.DTO.response.NoteDTO;
import my.learn.mireaffjpractice12.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteDTO toNoteDTO(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .updatedAt(note.getUpdatedAt())
                .createdAt(note.getCreatedAt())
                .build();
    }

}
