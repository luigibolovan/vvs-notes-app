package ro.upt.vvs.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.upt.vvs.notesapp.dao.NoteDao;
import ro.upt.vvs.notesapp.model.Note;

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
}
