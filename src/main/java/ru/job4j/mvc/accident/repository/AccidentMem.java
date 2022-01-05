package ru.job4j.mvc.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.mvc.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Данынй класс отвечает за хранилище происшествий (аварийных случаев - инцедентов)
 */

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private final AtomicInteger position = new AtomicInteger(0);

    public AccidentMem() {
        Accident first = new Accident(1, "First", "авария без жертв", "Варшавского");
        Accident second = new Accident(1, "Second", "авария c травсой человека", "Шоссе 3");
        add(first);
        add(second);
    }

    public void add(Accident accident) {
        accidents.put(position.get(), accident);
        position.set(position.get() + 1);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public List<Accident> getAll() {
        return List.copyOf(accidents.values());
    }

    public void update(int id, Accident accident) {
        if (accidents.get(id) != null) {
            accidents.put(id, accident);
        }
    }
}
