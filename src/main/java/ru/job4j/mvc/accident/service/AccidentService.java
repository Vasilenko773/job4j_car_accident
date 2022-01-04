package ru.job4j.mvc.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.job4j.di.Store;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.repository.AccidentMem;

import java.util.List;

/**
 * Указанынй класс отвечает за логику нашего приложения
 */

@Service
public class AccidentService {

    @Autowired
    private AccidentMem accidentMem;

   /* public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }*/

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public void get(int id) {
        accidentMem.get(id);
    }

    public List<Accident> findByAll() {
        return accidentMem.getAll();
    }
}
