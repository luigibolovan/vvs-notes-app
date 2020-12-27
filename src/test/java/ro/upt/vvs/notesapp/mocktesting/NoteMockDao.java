package ro.upt.vvs.notesapp.mocktesting;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.upt.vvs.notesapp.dao.NoteDao;
import ro.upt.vvs.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoteMockDao implements NoteDao {

    private List<Note> mockNotes = new ArrayList<>();

    @Override
    public Optional<Note> findById(String s) {
        return mockNotes.stream()
                .filter(note -> note.getUid().equals(s))
                .findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Note> findAll() {
        return mockNotes;
    }

    @Override
    public List<Note> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Note> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        Optional<Note> currentNote = findById(s);

        currentNote.ifPresent(note -> mockNotes.remove(currentNote.get()));
    }

    @Override
    public void delete(Note note) {

    }

    @Override
    public void deleteAll(Iterable<? extends Note> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Note> S save(S s) {
        return null;
    }

    @Override
    public <S extends Note> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Note> S saveAndFlush(S s) {
        mockNotes.add(s);
        return s;
    }

    @Override
    public void deleteInBatch(Iterable<Note> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Note getOne(String s) {
        return null;
    }

    @Override
    public <S extends Note> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Note> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Note> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Note> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Note> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Note> boolean exists(Example<S> example) {
        return false;
    }


}
