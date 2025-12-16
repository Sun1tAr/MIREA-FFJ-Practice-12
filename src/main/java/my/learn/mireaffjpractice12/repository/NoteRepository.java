package my.learn.mireaffjpractice12.repository;

import jakarta.validation.constraints.NotNull;
import my.learn.mireaffjpractice12.entity.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note,Long> {

    @NotNull
    List<Note> findAll();

}
