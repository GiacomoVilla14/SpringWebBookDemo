package it.itsrizzoli.webbookdemo.Controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    @NotNull
    @Size(min = 2, max = 30)
    String titolo;

    @NotNull
    @Size(min = 2, max = 50)
    String autore;

    @Min(1900)
    @Max(2024)
    int annoPubblicazione;

    @Min(5)
    @Max(100)
    int prezzo;

    public Book(String titolo, String autore, int annoPubblicazione, int prezzo) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public Book(String titolo, String autore, int annoPubblicazione) {

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

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }
}
