package ro.upt.vvs.notesapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.upt.vvs.notesapp.model.Note;
import ro.upt.vvs.notesapp.service.NoteService;

//@RestController
@Controller
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/addnote")
    public String addNote(@ModelAttribute("note") Note note){
        noteService.addNote(note);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllNotes(Model model) {
        Note newNote = new Note();
        model.addAttribute("note", newNote);
        model.addAttribute("listNotes", noteService.getAllNotes());
        return "index";
    }

    @GetMapping("/deletenote/{id}")
    public String deleteNoteByID(@PathVariable(value = "id") String id) {
        noteService.deleteNoteByID(id);

        return "redirect:/";
    }

    /* only rest */
    @PutMapping(path = "{id}")
    public void updateNoteByID(@PathVariable("id") String id, @RequestBody Note newNote) {
        noteService.updateNoteByID(id, newNote);
    }
}
