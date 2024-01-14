package it.itsrizzoli.webbookdemo.Controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonaForm {

    @NotNull
    @Size(min = 2, max = 30)
    String name;

    @NotNull
    @Size(min = 2, max = 20)
    String surname;

    @NotNull
    @Size(min = 4, max = 20)
    String username;

    @NotNull
    @Size(min = 8, max = 20)
    String password;

    public PersonaForm(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
