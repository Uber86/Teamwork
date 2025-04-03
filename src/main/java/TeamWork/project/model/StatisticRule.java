package TeamWork.project.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name ="rule_stats")
public class StatisticRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Rule rule;

    private Long count = 0L;

    public StatisticRule(long id, Rule rule, Long count) {
        this.id = id;
        this.rule = rule;
        this.count = count;
    }

    public StatisticRule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }
}
