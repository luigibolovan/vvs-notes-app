package ro.upt.vvs.notesapp.dao;

import ro.upt.vvs.notesapp.model.Note;

import java.util.UUID;

public interface NoteDao {

    int insertNote(UUID id, Note note);

    default int insertNote(Note note){
        UUID id = UUID.randomUUID();
        return insertNote(id, note);
    }

}
