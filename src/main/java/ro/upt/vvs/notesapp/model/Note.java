package ro.upt.vvs.notesapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Note {
    private final String text;
    private final UUID id;

    public UUID getUid() {
        return id;
    }

    public Note(@JsonProperty("id")UUID id, @JsonProperty("name")String text) {
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
