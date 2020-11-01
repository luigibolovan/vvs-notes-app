package ro.upt.vvs.notesapp.dao;

import org.springframework.stereotype.Repository;
import ro.upt.vvs.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dummyDao")
public class DummyNoteDataAccessService implements NoteDao{

    private static List<Note> dummyDB = new ArrayList<>();

    @Override
    public int insertNote(UUID id, Note note) {
        dummyDB.add(new Note(id, note.getText()));
        return 0;
    }

    @Override
    public List<Note> getAllNotes() {
        return dummyDB;
    }

    @Override
    public int updateNoteById(UUID id, Note note) {
        return getNoteById(id)
                .map(mynote -> {
                    int noteIndex = dummyDB.indexOf(mynote);
                    if (noteIndex >= 0) {
                        dummyDB.set(noteIndex, new Note(id, note.getText()));
                        return 0;
                    } else {
                        return 1;
                    }
                })
                .orElse(1);
    }

    @Override
    public Optional<Note> getNoteById(UUID id) {
       return dummyDB.stream()
                .filter(note -> note.getUid().equals(id))
                .findFirst();
    }

    @Override
    public int deleteNoteById(UUID id) {
        Optional<Note> note = getNoteById(id);

        if (!note.isPresent()){
            return 1;
        }
        dummyDB.remove(note.get());
        return 0;
    }
}
