package ru.job4j.mvc.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.mvc.accident.model.Accident;
import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query(value = "select distinct a from Accident a left join fetch a.rules")
    List<Accident> findAll();

    @Query(value = "select distinct a from Accident a left join fetch a.rules where a.id=?1")
    Accident findByIdAccident(int id);

}