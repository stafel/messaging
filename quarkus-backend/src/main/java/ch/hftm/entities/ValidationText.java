package ch.hftm.entities;

public class ValidationText {
    String id;
    String text;
    Boolean valid;

    public ValidationText(String id, String text, Boolean valid) {
        this.id = id;
        this.text = text;
        this.valid = valid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
