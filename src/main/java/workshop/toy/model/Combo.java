package workshop.toy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;

@JsonIgnoreProperties
public class Combo {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combo combo = (Combo) o;
        return Objects.equals(value, combo.value) &&
                Objects.equals(text, combo.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, text);
    }

    @JsonView
    private String value;
    @JsonView
    private String text;

    public Combo() {
    }

    public Combo(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
