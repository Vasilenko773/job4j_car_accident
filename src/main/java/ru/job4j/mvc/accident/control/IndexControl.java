package ru.job4j.mvc.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.repository.AccidentMem;
import ru.job4j.mvc.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс является контролером главной страницы приложения.
 */

@Controller
public class IndexControl {

    @Autowired
    private AccidentService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("date", service.springFindAllAccident());
        return "index";
    }
}