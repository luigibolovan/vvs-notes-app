//package ro.upt.vvs.notesapp.mockito;
//
//import ro.upt.vvs.notesapp.dao.NoteDao;
//import ro.upt.vvs.notesapp.model.Note;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public class NoteMockDao implements NoteDao {
//    private List<Note> mockNotes = new ArrayList<>();
//
//    public NoteMockDao() {
//        mockNotes.add(new Note(UUID.randomUUID(), "Note1"));
//        mockNotes.add(new Note(UUID.randomUUID(), "Note2"));
//        mockNotes.add(new Note(UUID.randomUUID(), "Note3"));
//        mockNotes.add(new Note(UUID.randomUUID(), "Note4"));
//    }
//
//    @Override
//    public int insertNote(UUID id, Note note) {
//        mockNotes.add(new Note(id, note.getText()));
//        return 0;
//    }
//
//    @Override
//    public List<Note> getAllNotes() {
//        return mockNotes;
//    }
//
//    @Override
//    public int deleteNoteById(UUID id) {
//        for(Note note : mockNotes){
//            if(note.getId().equals(id)){
//                mockNotes.remove(note);
//                return 0;
//            }
//        }
//        return 1;
//    }
//
//    @Override
//    public int updateNoteById(UUID id, Note note) {
//        for(Note mynote : mockNotes) {
//            if (mynote.getId().equals(id)) {
//                int index = mockNotes.indexOf(mynote);
//                mockNotes.set(index, note);
//                return 0;
//            }
//        }
//        return 1;
//    }
//
//    @Override
//    public Optional<Note> getNoteById(UUID id) {
//        return mockNotes.stream()
//                .filter(note -> note.getId().equals(id))
//                .findFirst();
//    }
//
//
//}
