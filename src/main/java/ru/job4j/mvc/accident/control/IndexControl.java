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

   /*private AccidentService service = new AccidentService(
            new AccidentMem(Accident.of(1, "accident",
            "Авария с травмой прохожего", "Свердлова 28")));*/

    @GetMapping("/")
    public String index(Model model) {
       /* service.add(Accident.of(1, "accident",
                "Авария с травмой прохожего", "Свердлова 28"));*/
        model.addAttribute("date", service.findByAll());
        return "index";
    }
}