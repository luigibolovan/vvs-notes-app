package ro.upt.vvs.notesapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.upt.vvs.notesapp.model.Note;
import ro.upt.vvs.notesapp.service.NoteService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/")
@RestController
public class NoteController {
    private final NoteService noteService;

    //dependency injection
    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping
    public void addNote(@RequestBody Note note){
        noteService.addNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping(path = "{id}")
    public Note getNoteByID(@PathVariable("id") UUID id) {
        return noteService.getNoteByID(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteNoteByID(@PathVariable("id") UUID id) {
        noteService.deleteNoteByID(id);
    }

    @PutMapping(path = "{id}")
    public void updateNoteByID(@PathVariable("id") UUID id, @RequestBody Note newNote) {
        noteService.updateNoteByID(id, newNote);
    }
}
