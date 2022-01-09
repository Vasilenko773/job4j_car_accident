package ru.job4j.mvc.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.job4j.di.Store;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;
import ru.job4j.mvc.accident.repository.AccidentJdbcTemplate;
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
    private AccidentJdbcTemplate accidentJdbcTemplate;


    /**
     * Начало работы d JDbS Template
     *
     * @return
     */
    public List<Accident> jdbcGetAll() {
        return accidentJdbcTemplate.jdbcGetAll();
    }

    public void jdbcSaveAccident(Accident accident, String[] rules) {
        accidentJdbcTemplate.save(accident);
        jdbcSaveRule(rules,
                jdbcGetAnIdAccident(accident.getName(), accident.getAddress()).getId());
    }

    private void jdbcSaveRule(String[] args, int accidentId) {
        for (String s : args) {
            accidentJdbcTemplate.saveRuleSet(Integer.valueOf(s), accidentId);
        }
    }

    private Accident jdbcGetAnIdAccident(String name, String address) {
        return accidentJdbcTemplate.findByNameFromAccident(name, address);
    }

    public List<Rule> jdbcFindAllRule() {
        return accidentJdbcTemplate.findAllRule();
    }

    public List<AccidentType> jdbcFindAllType() {
        return accidentJdbcTemplate.findAllType();
    }

    public Accident jdbcFundById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public void jdbcUpdate(Accident accident, String[] rules) {
        accidentJdbcTemplate.deleteRuleSet(jdbcGetAnIdAccident(accident.getName(), accident.getAddress()).getId());
        jdbcSaveRule(rules, jdbcGetAnIdAccident(accident.getName(), accident.getAddress()).getId());
    }

    public void jdbcSaveOrUpdate(Accident accident, String[] rules) {
        if (accidentJdbcTemplate.findById(accident.getId()) == null) {
           jdbcSaveAccident(accident, rules);
        } else {
            jdbcUpdate(accident, rules);
        }
    }
}




