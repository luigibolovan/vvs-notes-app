package ro.upt.vvs.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.upt.vvs.notesapp.dao.NoteDao;
import ro.upt.vvs.notesapp.model.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteDao noteDao;

    @Autowired
    public NoteService(@Qualifier("dummyDao")NoteDao noteDao){
        this.noteDao = noteDao;
    }

    public int addNote(Note note){
        return noteDao.insertNote(note);
    }

    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    public Optional<Note> getNoteByID(UUID id) {
        return noteDao.getNoteById(id);
    }

    public int deleteNoteByID(UUID id) {
        return noteDao.deleteNoteById(id);
    }

    public int updateNoteByID(UUID id, Note newNote) {
        return noteDao.updateNoteById(id, newNote);
    }
}
