package it.itsrizzoli.webbookdemo.Controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

public class BookController {

    public ArrayList<Book> books = new ArrayList<>();

    @GetMapping("/createBook")
    public String createBook() {
        return "createBook";
    }

    @PostMapping("/postBookStore")
    public String postBookStore(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createBook";
        }

        books.add(book);
        model.addAttribute("books", books);
        return ("redirect:/home");

    }

}
