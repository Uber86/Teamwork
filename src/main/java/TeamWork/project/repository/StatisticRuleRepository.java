package TeamWork.project.repository;

import TeamWork.project.model.Rule;
import TeamWork.project.model.StatisticRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Интерфейс-репозитории StatisticRuleRepository
 * для работы со счетчиком правил хранящихся в БД
 */
@Repository
public interface StatisticRuleRepository extends JpaRepository<StatisticRule, Long> {

    Optional<StatisticRule> findByRule(Rule rule);

}
