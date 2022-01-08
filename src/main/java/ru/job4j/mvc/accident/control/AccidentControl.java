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
        model.addAttribute("rules", service.jdbcFindAllRule());
        model.addAttribute("types", service.jdbcFindAllType());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident, HttpServletRequest req,
                       @RequestParam("type.id") int id) {
        String[] ids = req.getParameterValues("rIds");
        service.jdbcSaveOrUpdate(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", service.jdbcFindAllType());
        model.addAttribute("accident", service.jdbcFundById(id));
        return "accident/edit";
    }
}

