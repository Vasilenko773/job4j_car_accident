package ru.job4j.mvc.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;

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

    private HashMap<Integer, AccidentType> types = new HashMap<>();

    private final AtomicInteger typeCount = new AtomicInteger(0);

    private final AtomicInteger position = new AtomicInteger(0);

    public AccidentMem() {
        addType(AccidentType.of(1, "Две машины"));
        addType(AccidentType.of(2, "Машина и человек"));
        addType(AccidentType.of(3, "Машина и велосипед"));
        add(new Accident(1, "First", "авария без жертв", "Варшавского", types.get(1)));
        add(new Accident(2, "Second", "авария c травсой человека", "Шоссе 3", types.get(2)));
    }

    public void add(Accident accident) {
        accidents.put(position.incrementAndGet(), accident);
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

    public void addType(AccidentType type) {
        types.put(typeCount.incrementAndGet(), type);
    }

    public AccidentType getType(int id) {
        return types.get(id);
    }

    public List<AccidentType> getAllType() {
        return List.copyOf(types.values());
    }

}
