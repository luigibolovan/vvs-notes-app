package ro.upt.vvs.notesapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Note {
    private String text;
    private final UUID id;

    public UUID getUid() {
        return id;
    }

    public Note(@JsonProperty("id")UUID id, @JsonProperty("text")String text) {
        this.id = id;
        this.text = text;
    }

    public Note() {
        id = UUID.randomUUID();
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
