package ru.job4j.mvc.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService service;
    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", service.springFindAllRule());
        model.addAttribute("types", service.springFindAllType());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident, HttpServletRequest req,
                       @RequestParam("type.id") int id) {
        String[] ids = req.getParameterValues("rIds");
        service.springSaveOrUpdate(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", service.springFindAllType());
        model.addAttribute("accident", service.springAccidentById(id));
        return "accident/edit";
    }
}

