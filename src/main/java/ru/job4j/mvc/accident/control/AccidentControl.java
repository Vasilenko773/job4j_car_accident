package ru.job4j.mvc.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;
import ru.job4j.mvc.accident.repository.AccidentMem;
import ru.job4j.mvc.accident.repository.RulesMem;
import ru.job4j.mvc.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {

    private final AccidentService service;


    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", service.findByAllRule());
        model.addAttribute("types", service.findByAllType());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident, HttpServletRequest req,
                       @RequestParam("type.id") int id) {
        String[] ids = req.getParameterValues("rIds");
        service.saveRuleList(ids, accident);

        accident.setType(service.getType(id));
        if (service.get(accident.getId()) != null) {
            service.updateAccident(accident.getId(), accident);
        } else {
            service.add(accident);
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", service.findByAllType());
        model.addAttribute("accident", service.get(id));
        return "accident/edit";
    }

   /*@GetMapping("/edit")
    public String edit(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") String id, @ModelAttribute("accident") Accident accident) {
        accidents.update(Integer.valueOf(id), accident);
        return "redirect:/";
    }*/
}