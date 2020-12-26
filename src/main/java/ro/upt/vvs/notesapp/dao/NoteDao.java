package ro.upt.vvs.notesapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.upt.vvs.notesapp.model.Note;

public interface NoteDao extends JpaRepository<Note, String> {
}
