package ro.upt.vvs.notesapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.upt.vvs.notesapp.model.Note;
import ro.upt.vvs.notesapp.service.NoteService;

@RequestMapping("/")
@RestController
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping
    public void addNote(@RequestBody Note note){
        noteService.addNote(note);
    }
}
