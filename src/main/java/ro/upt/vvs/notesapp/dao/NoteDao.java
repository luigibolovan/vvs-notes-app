package ro.upt.vvs.notesapp.dao;

import ro.upt.vvs.notesapp.model.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteDao {

    int insertNote(UUID id, Note note);

    default int insertNote(Note note){
        UUID id = UUID.randomUUID();
        return insertNote(id, note);
    }

    List<Note> getAllNotes();

    int deleteNoteById(UUID id);

    int updateNoteById(UUID id, Note note);

    Optional<Note> getNoteById(UUID id);
}
