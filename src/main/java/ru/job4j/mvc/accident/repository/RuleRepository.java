package ru.job4j.mvc.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mvc.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {


}
