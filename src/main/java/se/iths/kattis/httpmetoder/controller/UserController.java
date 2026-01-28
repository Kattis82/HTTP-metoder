package se.iths.kattis.httpmetoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.kattis.httpmetoder.model.User;
import se.iths.kattis.httpmetoder.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Visa alla användare
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // Thymeleaf template: users.html
    }

    // Visa formulär för att skapa ny användare
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "create_user"; // Thymeleaf template: create_user.html
    }

    // Skapa användare
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    // Visa formulär för att uppdatera användare
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "edit_user"; // Thymeleaf template: edit_user.html
    }

//    Ingen HTML-sida till nedan:
//    Man tar bort direkt och skickar sedan användaren tillbaka till listan (redirect:/users).
//    Det behövs ingen separat vy, eftersom man inte visar någon ny information, bara uppdaterar listan.

    // Uppdatera användare
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    // Ta bort användare
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
