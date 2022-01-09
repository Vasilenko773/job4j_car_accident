package ru.job4j.mvc.accident.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.mvc.accident.model.Accident;
import ru.job4j.mvc.accident.model.AccidentType;
import ru.job4j.mvc.accident.model.Rule;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId());
        return accident;
    }

    public List<Accident> jdbcGetAll() {
        return jdbc.query("select a.id aId, a.name aName, a.text aText,"
                        + " a.address aAddress, a.type_id atId, t.name tName"
                        + " from accidents as a left join types t on a.type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("aId"));
                    accident.setName(rs.getString("aName"));
                    accident.setText(rs.getString("aText"));
                    accident.setAddress(rs.getString("aAddress"));
                    accident.setType(AccidentType.of(rs.getInt("atId"), rs.getString("tName")));
                    accident.setRules(getAllRules(rs.getInt("aId")));
                    return accident;
                });
    }

    private Set<Rule> getAllRules(int id) {
        return new HashSet<>(jdbc.query("select r.id rId, r.name rName from rules as r join accident_rule ar on r.id = ar.rule_id"
                + " join accidents a on a.id = ar.accident_id where a.id = ?", (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("rId"));
            rule.setName(rs.getString("rName"));
            return rule;
        }, id));
    }

    public void saveRuleSet(int idRule, int idAccident) {
        jdbc.update("insert into accident_rule (rule_id, accident_id) values (?, ?)", idRule, idAccident);
    }

    public void deleteRuleSet(int idAccident) {
        jdbc.update("delete from accident_rule where accident_id = ?;", idAccident);
    }

    public Accident findByNameFromAccident(String name, String address) {
        return jdbc.query("select a.id aId, a.name aName, a.text aText,"
                + " a.address aAddress, a.type_id atId, t.name tName"
                + " from accidents as a left join types t on a.type_id = t.id where a.name = ? and a.address = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("aId"));
            accident.setName(rs.getString("aName"));
            accident.setText(rs.getString("aText"));
            accident.setAddress(rs.getString("aAddress"));
            accident.setType(AccidentType.of(rs.getInt("atId"), rs.getString("tName")));
            accident.setRules(getAllRules(rs.getInt("aId")));
            return accident;
        }, name, address).stream().findFirst().orElse(null);
    }

    public Accident findById(int id) {
        return jdbc.query("select a.id aId, a.name aName, a.text aText,"
                + " a.address aAddress, a.type_id atId, t.name tName"
                + " from accidents as a left join types t on a.type_id = t.id where a.id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("aId"));
            accident.setName(rs.getString("aName"));
            accident.setText(rs.getString("aText"));
            accident.setAddress(rs.getString("aAddress"));
            accident.setType(AccidentType.of(rs.getInt("atId"), rs.getString("tName")));
            accident.setRules(getAllRules(rs.getInt("aId")));
            return accident;
        }, id).stream().findFirst().orElse(null);
    }

    public void update(Accident accident) {
        jdbc.update("update accidents set (name, text, address, type_id) = (?, ?, ?, ?) where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(), accident.getId());
    }

    public List<Rule> findAllRule() {
        return jdbc.query("select * from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<AccidentType> findAllType() {
        return jdbc.query("select t.id tId, t.name tName from types as t",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("tId"));
                    type.setName(rs.getString("tName"));
                    return type;
                });
    }
}







