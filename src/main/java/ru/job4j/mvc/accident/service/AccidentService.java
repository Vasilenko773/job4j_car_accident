package ru.job4j.mvc.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;
import ru.job4j.mvc.accident.model.User;
import ru.job4j.mvc.accident.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Указанынй класс отвечает за логику нашего приложения
 */

@Service
public class AccidentService {


  /*  @Autowired
    private AccidentJdbcTemplate accidentJdbcTemplate;

    @Autowired
    private AccidentHibernate accidentHibernate;

   */

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private AccidentRepository accidentRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private UserRepository userRepository;

  /*
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
        accidentJdbcTemplate.deleteRuleSet(accident.getId());
        jdbcSaveRule(rules, accident.getId());
        accidentJdbcTemplate.update(accident);
    }

    @Transactional
    public void jdbcSaveOrUpdate(Accident accident, String[] rules) {
        if (accidentJdbcTemplate.findById(accident.getId()) == null) {
            jdbcSaveAccident(accident, rules);
        } else {
            jdbcUpdate(accident, rules);
        }
    }



    public List<Accident> hibernateFindAllAccident() {
        return accidentHibernate.getAll();
    }

    public Accident hibernateFindAccidentById(int id) {
        return accidentHibernate.findByAccidentById(id);
    }

    public List<AccidentType> hibernateFindAllType() {
        return accidentHibernate.findAllType();
    }

    public List<Rule> hibernateFindAllRule() {
        return accidentHibernate.findAllRule();
    }

    @Transactional
    public void hibernateSaveOrUpdateAccident(Accident accident, String[] rules) {
        accidentHibernate.saveOrUpdate(accident, rules);
    }

   */

    public List<Rule> springFindAllRule() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    public List<AccidentType> springFindAllType() {
        List<AccidentType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }

    public List<Accident> springFindAllAccident() {
        return accidentRepository.findAll();
    }

    public Accident springAccidentById(int id) {
        return accidentRepository.findByIdAccident(id);
    }

    public Rule springGetRuleById(int id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public void springSaveOrUpdate(Accident accident, String[] rules) {
        accident.getRules().clear();
        for (String s : rules) {
            accident.addRule(springGetRuleById(Integer.valueOf(s)));
        }
        accidentRepository.save(accident);
    }

}





