package ru.job4j.mvc.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import ru.job4j.mvc.accident.model.User;
import ru.job4j.mvc.accident.repository.AuthorityRepository;
import ru.job4j.mvc.accident.repository.UserRepository;
import ru.job4j.mvc.accident.service.AccidentService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (users.findByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Пользователь с указанны username уже существует, попробуйте снова");
            return "reg";
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}