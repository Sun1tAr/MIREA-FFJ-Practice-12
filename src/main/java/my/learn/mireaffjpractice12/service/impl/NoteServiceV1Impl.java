package my.learn.mireaffjpractice12.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.DTO.request.CreateNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PatchNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PutNoteRequest;
import my.learn.mireaffjpractice12.entity.Note;
import my.learn.mireaffjpractice12.exception.ConflictException;
import my.learn.mireaffjpractice12.exception.NotFoundException;
import my.learn.mireaffjpractice12.repository.NoteRepository;
import my.learn.mireaffjpractice12.service.NoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteServiceV1Impl  implements NoteService {

    private final NoteRepository noteRepository;


    @Override
    public Note addNote(CreateNoteRequest request) {
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new ConflictException(e.getMessage());
        }
    }

    @Override
    public Note patchNote(PatchNoteRequest req, Long id) {
        Note note = findById(id);
        req.getFields().forEach((field,value)->{
            switch (field) {
                case "title": note.setTitle(req.getFields().get(field).toString());
                break;
                case "content": note.setContent(req.getFields().get(field).toString());
                break;
            }
        });
        note.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @Override
    public Note putNote(PutNoteRequest req, Long id) {
        Note note = findById(id);
        
        note.setContent(req.getContent());
        note.setUpdatedAt(LocalDateTime.now());
        note.setTitle(req.getTitle());

        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new ConflictException(e.getMessage());
        }
    }

    @Override
    public void deleteNote(Long id) {
        try {
            noteRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isEmpty()) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        return noteOptional.get();
    }
}
