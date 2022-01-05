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

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {

    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        model.addAttribute("types", types);

        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rsl = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            rsl.add(Rule.of(i + 1, ids[i]));
        }
        accident.setRules(rsl);
        accidents.add(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
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
    }
}