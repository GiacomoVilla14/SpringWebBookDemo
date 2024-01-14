package it.itsrizzoli.webbookdemo.Controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    @NotNull
    @Size(min = 2, max = 30)
    String titolo;

    @NotNull
    @Size(min = 2, max = 50)
    String autore;

    @Size(min = 0, max = 30)
    String descrizione;

    public Book(String titolo, String autore, String descrizione) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
