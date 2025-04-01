package TeamWork.project.repository;

import TeamWork.project.model.Rule;
import TeamWork.project.model.StatisticRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticRuleRepository extends JpaRepository<StatisticRule, Long> {

    Optional<StatisticRule> findByRule(Rule rule);

}
