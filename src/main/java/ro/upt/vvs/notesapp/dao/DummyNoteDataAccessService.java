package ro.upt.vvs.notesapp.dao;

import org.springframework.stereotype.Repository;
import ro.upt.vvs.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("dummyDao")
public class DummyNoteDataAccessService implements NoteDao{

    private static List<Note> dummyDB = new ArrayList<>();

    @Override
    public int insertNote(UUID id, Note note) {
        dummyDB.add(new Note(id, note.getText()));
        return 0;
    }
}
