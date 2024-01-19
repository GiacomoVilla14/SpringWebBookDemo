package it.itsrizzoli.webbookdemo.Controller;

import it.itsrizzoli.webbookdemo.Model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookUserRepository bookUserRepository;
    static ArrayList<Book> books = new ArrayList<>();

    @GetMapping("/createbook")
    public String createBook(Book book, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "createbook";
    }

    @PostMapping("/postbookstore")
    public String postBookStore(@Valid Book book, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");

        if (bindingResult.hasErrors()) {
            return "createbook";
        }
        bookRepository.save(book);
        return "redirect:/home";

    }

    @GetMapping("/bookdetail")
    public String bookDetail(@RequestParam("bookId") Integer bookId, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Optional<Book> oBook = bookRepository.findById(bookId);
        Book book = null;
        if (oBook.isPresent()) {
            book = oBook.get();
        }
        model.addAttribute("book", book);

        return "bookdetail";

    }

    @RequestMapping("/add")
    public String addBook(@RequestParam("bookId") Integer bookId, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Optional<Book> bookToAdd = bookRepository.findById(bookId);
        User user = (User) session.getAttribute("user");
        Book book = null;
        if (bookToAdd.isPresent()) {
            book = bookToAdd.get();
            books.add(book);
            bookRepository.save(book);
        }
        BookUser bookUser = new BookUser(book, user);
        bookUserRepository.save(bookUser);
        return "redirect:/home";
    }


}
