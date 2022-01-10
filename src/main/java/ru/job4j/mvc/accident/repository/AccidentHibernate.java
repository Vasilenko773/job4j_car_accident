package ru.job4j.mvc.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private Rule getRule(int id) {
        try (Session session = sf.openSession()) {
            return (Rule) session.createQuery("from Rule where id =: id")
                    .setParameter("id", id).list()
                    .stream().findFirst().orElse(null);
        }
    }

    public void saveOrUpdate(Accident accident, String[] rules) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accident.getRules().clear();
            for (String s : rules) {
                accident.getRules().add(getRule(Integer.valueOf(s)));
            }
            session.saveOrUpdate(accident);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct a from Accident a left join fetch a.rules").list();
        }
    }

    public List<Rule> findAllRule() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule").list();
        }
    }

    public List<AccidentType> findAllType() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType").list();
        }
    }

    public Accident findByAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return (Accident) session.createQuery("select distinct a from Accident a left join fetch a.rules where a.id =: id")
                    .setParameter("id", id).list()
                    .stream().findFirst().orElse(null);
        }
    }

    /*public Accident findByAccidentByName(String name) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            return (Accident) session.createQuery("from Accident a where a.name = :name")
                    .setParameter("name", name).list()
                    .stream().findFirst().orElse(null);
        }
    }

    public void updateAccident(Accident accident, String[] rules) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accident.getRules().clear();
            for (String s : rules) {
                accident.getRules().add(getRule(Integer.valueOf(s)));
            }
            session.merge(accident);
            session.getTransaction().commit();
        }
    }

      public void save(Accident accident, String[] rules) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (String s : rules) {
                accident.getRules().add(getRule(Integer.valueOf(s)));
            }
            session.save(accident);
            session.getTransaction().commit();
            session.close();
        }
    }

     */
}