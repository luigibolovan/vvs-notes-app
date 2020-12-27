package ro.upt.vvs.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upt.vvs.notesapp.dao.NoteDao;
import ro.upt.vvs.notesapp.model.Note;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteDao noteDao;

    @Autowired
    public NoteService(NoteDao noteDao){
        this.noteDao = noteDao;
    }

    public void addNote(Note note){
        if (note.getText() != null && !note.getText().equals("")) {
            noteDao.saveAndFlush(note);
        }
    }

    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }

    public Optional<Note> getNoteByID(String id) {
        return noteDao.findById(id);
    }

    public void deleteNoteByID(String uid) {
        noteDao.deleteById(uid);
    }

    public void updateNoteByID(String id, Note newNote) {
        Optional<Note> currentNote = noteDao.findById(id);

        currentNote.ifPresent(note -> {
            note.setText(newNote.getText());
            noteDao.saveAndFlush(note);
        });

    }
}
