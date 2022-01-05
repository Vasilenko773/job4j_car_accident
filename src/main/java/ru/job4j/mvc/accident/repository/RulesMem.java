package ru.job4j.mvc.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RulesMem {

    private HashMap<Integer, Rule> rules = new HashMap<>();

    private final AtomicInteger position = new AtomicInteger(0);

    public RulesMem() {
        addRule(Rule.of(1, "Статья. 1"));
        addRule(Rule.of(2, "Статья. 2"));
        addRule(Rule.of(3, "Статья. 3"));
    }

    public void addRule(Rule rule) {
        rules.put(position.incrementAndGet(), rule);
    }

    public Rule getRule(int id) {
        return rules.get(id);
    }

    public Set<Rule> getAllRule() {
        return Set.copyOf(rules.values());
    }

    public void saveRuleList(String[] array, Accident accident) {
        Set<Rule> list = new HashSet<>();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                list.add(Rule.of(Integer.valueOf(array[i]), rules.get(Integer.valueOf(array[i])).getName()));
            }
            accident.setRules(list);
        }
    }
}
