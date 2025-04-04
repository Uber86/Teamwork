package TeamWork.project.model;

import jakarta.persistence.*;

/**
 * Класс StatisticRule
 * статический счетчик
 * привязан к правилам по id
 */
@Entity
@Table(name ="rule_stats")
public class StatisticRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     *Двусторонние отношения
     * id продукта
     */
    @ManyToOne
    @JoinColumn(name ="rule_id")
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
