package ro.upt.vvs.notesapp.mocktesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.upt.vvs.notesapp.model.Note;
import ro.upt.vvs.notesapp.service.NoteService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteMockServiceTest {
    NoteService serviceToTest;

    @BeforeEach
    public void instantiateService() {
        serviceToTest = new NoteService(new NoteMockDao());
    }


    @Test
    public void whenAddingNoTextNote_itNotAddedInDB() {
        serviceToTest.addNote(new Note());
        List<Note> notesInDB = serviceToTest.getAllNotes();
        assertEquals(notesInDB.size(), 0);
    }

    @Test
    public void whenAddingTextNote_itIsAddedInDB() {
        serviceToTest.addNote(new Note("Abcdefghijklmn"));
        List<Note> notesInDB = serviceToTest.getAllNotes();
        assertEquals(notesInDB.size(), 1);
    }

    @Test
    public void whenAddingNullNote_NPEthrown() {
        assertThrows(NullPointerException.class, () -> {
            serviceToTest.addNote(null);
        });
    }

    @Test
    public void whenDeletingExistingNote_EntryInDBNotPresentAnymore() {
        UUID noteUUID = UUID.randomUUID();
        Note testNote = new Note(noteUUID, "abcdef");
        serviceToTest.addNote(testNote);
        assertEquals(serviceToTest.getAllNotes().size(), 1);
        serviceToTest.deleteNoteByID(noteUUID.toString());
        assertEquals(serviceToTest.getAllNotes().size(), 0);
    }

    @Test
    public void whenDeletingNoteWithInvalidId_NothingIsDeleted() {
        UUID noteUUID = UUID.randomUUID();
        Note testNote = new Note(noteUUID, "abcdef");
        serviceToTest.addNote(testNote);
        assertEquals(serviceToTest.getAllNotes().size(), 1);
        UUID anotherUUID = UUID.randomUUID();
        serviceToTest.deleteNoteByID(anotherUUID.toString());
        assertEquals(serviceToTest.getAllNotes().size(), 1);
    }

    @Test
    public void whenDeletingNoteUsingNullId_NothingIsDeleted() {
        UUID noteUUID = UUID.randomUUID();
        Note testNote = new Note(noteUUID, "nwqiodoq");
        serviceToTest.addNote(testNote);
        assertEquals(serviceToTest.getAllNotes().size(), 1);
        serviceToTest.deleteNoteByID(null);
        assertEquals(serviceToTest.getAllNotes().size(), 1);
    }
}
