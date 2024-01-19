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

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookUserRepository bookUserRepository;


    @GetMapping("/registeruser")
    public String registerUser(UserForm userForm, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "userRegistration";
    }

    @PostMapping("/postreguser")
    public String postRegUser(@Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        userRepository.save(new User(userForm.name, userForm.surname, userForm.username, userForm.password));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(LoginForm loginForm, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "loginPage";
    }

    @PostMapping("/postlogin")
    public String postLogin(LoginForm loginForm, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        User user = userRepository.login(loginForm.username, loginForm.password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            return "loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout(UserForm userForm, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        session.setAttribute("user", null);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String showHome(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("books", bookRepository.findBooksByBookUser(user.getId()));
        model.addAttribute("allBooks", bookRepository.findAll());
        return "home";
    }

    @GetMapping("/remove")
    public String removeBook(@RequestParam("bookId") Integer bookId, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Optional<Book> bookToRemove = bookRepository.findById(bookId);
        if (bookToRemove.isPresent()) {
            Book book = bookToRemove.get();
            bookRepository.delete(book);
        }
        return "redirect:/home";
    }

    @RequestMapping("/removeBookUser")
    public String removeBookUser(@RequestParam("bookId") Integer bookId, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        Optional<BookUser> removeBook = bookUserRepository.findBooksByUserBooks(user.getId(), bookId);

        if (removeBook.isPresent()) {
            BookUser userBook = removeBook.get();
            bookUserRepository.delete(userBook);
        }

        return "redirect:/home";
    }

}
