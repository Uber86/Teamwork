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

    @Enumerated(EnumType.STRING)
    @Column(name = "query")
    private EnumType query;

    @Column(name = "arguments")
    private List<String> arguments;

    @Column(name = "negate")
    private boolean negate;

    @ManyToOne
    private Rule rule;

    public Query(long id, EnumType query, List<String> arguments, boolean negate, Rule rule) {
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

    public EnumType getQuery() {
        return query;
    }

    public void setQuery(EnumType query) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query1 = (Query) o;
        return id == query1.id && negate == query1.negate && query == query1.query && Objects.equals(arguments, query1.arguments) && Objects.equals(rule, query1.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, query, arguments, negate, rule);
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", query=" + query +
                ", arguments=" + arguments +
                ", negate=" + negate +
                ", rule=" + rule +
                '}';
    }
}
