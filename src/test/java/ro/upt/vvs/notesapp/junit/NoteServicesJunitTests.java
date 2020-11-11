package ro.upt.vvs.notesapp.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.upt.vvs.notesapp.dao.DummyNoteDataAccessService;
import ro.upt.vvs.notesapp.model.Note;
import ro.upt.vvs.notesapp.service.NoteService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteServicesJunitTests {

    private NoteService addService;
    private NoteService getAllService;
    private NoteService deleteService;
    private NoteService updateService;

    private List<Note> notes;

    @BeforeEach
    public void initEachTest(){
        notes = null;
        addService = null;
        getAllService = null;
        deleteService = null;
        updateService = null;
    }

    @AfterEach
    public void teardown(){
        addService = null;
        getAllService = null;
        deleteService = null;
        updateService = null;
        notes = null;

    }


    @Test
    public void whenAddingValidNote_GetPositiveResult(){
        addService = new NoteService(new DummyNoteDataAccessService());
        int result = addService.addNote((new Note(UUID.randomUUID(), "PostTesting")));
        assertEquals(result, 0);
    }

    @Test
    public void whenAddingNullNote_GetErrorCode(){
        addService = new NoteService(new DummyNoteDataAccessService());
        int result = addService.addNote(null);
        assertEquals(result, 1);
    }

    @Test
    public void whenAddingNullID_NoteWillBeCreatedSuccessfullyWithRandomUID(){
        addService = new NoteService(new DummyNoteDataAccessService());
        int result = addService.addNote(new Note(null, "PostTesting"));
        assertEquals(result, 0);
    }

    @Test
    public void whenAddingNullNoteText_GetErrorCode(){
        addService = new NoteService(new DummyNoteDataAccessService());
        int result = addService.addNote(new Note(UUID.randomUUID(), null));
        assertEquals(result, 1);
    }

    @Test
    public void whenOneNoteOnly_getOnlyOneNote(){
        getAllService = new NoteService(new DummyNoteDataAccessService());
        getAllService.addNote(new Note(null, "GetOnlyOneNote"));
        notes = getAllService.getAllNotes();
        assertEquals(notes.size() , 1);
    }

    @Test
    public void whenTwoNotesOnly_getTwoNotes(){
        getAllService = new NoteService(new DummyNoteDataAccessService());
        getAllService.addNote(new Note(null, "GetFirstNote"));
        getAllService.addNote(new Note(null, "GetSecondNote"));
        notes = getAllService.getAllNotes();
        assertEquals(notes.size() , 2);
    }

    @Test
    public void whenNoNotes_getNothing(){
        getAllService = new NoteService(new DummyNoteDataAccessService());
        notes = getAllService.getAllNotes();
        assertEquals(notes.size(), 0);
    }

    @Test
    public void whenDeletingOneExistingNote_GetSuccessCode(){
        deleteService = new NoteService(new DummyNoteDataAccessService());
        deleteService.addNote(new Note(UUID.randomUUID(), "Delete1"));
        deleteService.addNote(new Note(UUID.randomUUID(), "Delete2"));

        notes = deleteService.getAllNotes();
        UUID firstNoteID = notes.get(0).getUid();
        int result = deleteService.deleteNoteByID(firstNoteID);
        assertEquals(0, result);
        assertEquals(notes.size(), 1);
    }

    @Test
    public void whenDeletingOneNonexistingNote_GetErrorCode(){
        deleteService = new NoteService(new DummyNoteDataAccessService());
        deleteService.addNote(new Note(UUID.randomUUID(), "Delete1"));
        deleteService.addNote(new Note(UUID.randomUUID(), "Delete2"));

        notes = deleteService.getAllNotes();
        UUID randomUUID = UUID.randomUUID();
        int result = deleteService.deleteNoteByID(randomUUID);
        assertEquals(1, result);
        assertEquals(2, notes.size());
    }

    @Test
    public void whenUpdatingOneExistingNote_UpdateSuccess() {
        updateService = new NoteService(new DummyNoteDataAccessService());
        updateService.addNote(new Note(UUID.randomUUID(), "Text"));

        notes = updateService.getAllNotes();
        UUID noteID = notes.get(0).getUid();
        int result = updateService.updateNoteByID(noteID, new Note(noteID, "UpdatedText"));
        assertEquals(0, result);
        assertEquals(notes.get(0).getText(), "UpdatedText");
    }

    @Test
    public void whenUpdatingNonExistingNote_UpdateError() {
        updateService = new NoteService(new DummyNoteDataAccessService());
        updateService.addNote(new Note(UUID.randomUUID(), "Text123"));

        notes = updateService.getAllNotes();
        UUID randomNoteID = UUID.randomUUID();

        int result = updateService.updateNoteByID(randomNoteID, new Note(randomNoteID, "Hello"));
        assertEquals(1, result);
        assertEquals(notes.get(0).getText(), "Text123");
    }
}
