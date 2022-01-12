package ru.job4j.mvc.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mvc.accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}