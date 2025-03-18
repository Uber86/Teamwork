package TeamWork.project.model;

import TeamWork.project.dto.Querys;
import TeamWork.project.repository.RecommendationRepository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "query")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "query")
    private Enum<Querys> query;

    @Column(name = "arguments")
    private List<String> arguments;

    @Column(name = "negate")
    private boolean negate;

    @ManyToOne
    private Rule rule;

    public Query(long id, Enum<Querys> query, List<String> arguments, boolean negate, Rule rule) {
        this.id = id;
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
        this.rule = rule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum<Querys> getQuery() {
        return query;
    }

    public void setQuery(Enum<Querys> query) {
        this.query = query;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
