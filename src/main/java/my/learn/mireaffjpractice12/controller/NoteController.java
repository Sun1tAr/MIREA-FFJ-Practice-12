package my.learn.mireaffjpractice12.controller;

import jakarta.validation.Valid;
import my.learn.mireaffjpractice12.DTO.request.CreateNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PatchNoteRequest;
import my.learn.mireaffjpractice12.DTO.request.PutNoteRequest;
import my.learn.mireaffjpractice12.DTO.response.NoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface NoteController extends HealthController {


    @PostMapping
    ResponseEntity<NoteDTO> addNote(@RequestBody @Valid CreateNoteRequest createNoteRequest);

    @PatchMapping("/{id}")
    ResponseEntity<NoteDTO> patchNote(@RequestBody @Valid PatchNoteRequest patchNoteRequest, @PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<NoteDTO> putNote(@RequestBody @Valid PutNoteRequest putNoteRequest, @PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteNote(@PathVariable(name = "id") Long id);

    @GetMapping
    ResponseEntity<List<NoteDTO>> getAllNotes();

    @GetMapping("/{id}")
    ResponseEntity<NoteDTO> getNoteById(@PathVariable(name = "id") Long id);



}
