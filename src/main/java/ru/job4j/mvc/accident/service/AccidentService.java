package ru.job4j.mvc.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.job4j.di.Store;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;
import ru.job4j.mvc.accident.repository.AccidentMem;
import ru.job4j.mvc.accident.repository.RulesMem;

import java.util.List;
import java.util.Set;

/**
 * Указанынй класс отвечает за логику нашего приложения
 */

@Service
public class AccidentService {

    @Autowired
    private AccidentMem accidentMem;

    @Autowired
    private RulesMem rulesMem;

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public Accident get(int id) {
        return accidentMem.get(id);
    }

    public List<Accident> findByAllAccident() {
        return accidentMem.getAll();
    }

    public void addType(AccidentType type) {
        accidentMem.addType(type);
    }

    public void updateAccident(int id, Accident accident) {
        accidentMem.update(id, accident);
    }

    public AccidentType getType(int id) {
        return accidentMem.getType(id);
    }

    public List<AccidentType> findByAllType() {
        return accidentMem.getAllType();
    }

    public void addRule(Rule rule) {
        rulesMem.addRule(rule);
    }

    public Rule getRule(int id) {
       return rulesMem.getRule(id);
    }

    public Set<Rule> findByAllRule() {
        return rulesMem.getAllRule();
    }

    public void saveRuleList(String[] array, Accident accident) {
        rulesMem.saveRuleList(array, accident);
    }

    public void saveOrUpdate(Accident accident) {
        if (accidentMem.get(accident.getId()) != null) {
            accidentMem.update(accident.getId(), accident);
        } else {
            accidentMem.add(accident);
        }
    }
}
