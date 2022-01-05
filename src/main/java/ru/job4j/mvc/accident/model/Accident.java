package ru.job4j.mvc.accident.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Accident {

    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;

    public static Accident of(int id, String name, String text, String address, AccidentType type) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        return accident;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name) && Objects.equals(text, accident.text) && Objects.equals(address, accident.address) && Objects.equals(type, accident.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address, type);
    }

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", address='" + address + '\''
                + ", type=" + type
                + '}';
    }
}
