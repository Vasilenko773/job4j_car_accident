package ru.job4j.mvc.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList<>(List.of("First", "Second", "Third"));
        model.addAttribute("date", list);
        return "index";
    }
}