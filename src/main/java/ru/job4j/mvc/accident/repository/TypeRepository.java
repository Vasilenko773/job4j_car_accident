package ru.job4j.mvc.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mvc.accident.model.AccidentType;

public interface TypeRepository extends CrudRepository<AccidentType, Integer> {
}
