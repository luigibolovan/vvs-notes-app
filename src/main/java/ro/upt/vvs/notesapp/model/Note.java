package ro.upt.vvs.notesapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="notes")
public class Note {
    private String uid;
    private String text;

    @Id
    public String getUid() {
        return uid;
    }

    public Note(@JsonProperty("id")UUID id, @JsonProperty("text")String text) {
        this.uid = id.toString();
        this.text = text;
    }

    public Note() {
        uid = UUID.randomUUID().toString();
    }

    public Note(String text) {
        uid = UUID.randomUUID().toString();
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUid(String id) {
        this.uid = id;
    }
}
