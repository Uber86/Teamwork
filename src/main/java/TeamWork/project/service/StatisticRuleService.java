package TeamWork.project.service;

import TeamWork.project.model.Rule;
import TeamWork.project.model.StatisticRule;
import TeamWork.project.repository.RuleRepository;
import TeamWork.project.repository.StatisticRuleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс-сервис StatisticRuleService
 * для работы со статическими счетчиками правил
 */
@Service
public class StatisticRuleService {

    @Autowired
    private StatisticRuleRepository statisticRuleRepository;

    @Autowired
    private RuleRepository ruleRepository;


    /**
     * Метод увеличивает статистику при каждом вызове правила
     * @param ruleId идентификатор правила
     */
    @Transactional
    public void incrementStatistic(Long ruleId) {
        Rule rule = ruleRepository.findAll().stream()
                .filter(it -> it.getId() == ruleId)
                .findFirst().orElseThrow(() ->
                        new EntityNotFoundException("Такого правила не существует"));

        StatisticRule stat = statisticRuleRepository.findByRule(rule)
                .orElseGet(() -> createNewStatic(rule));
        stat.incrementCount();
        statisticRuleRepository.save(stat);
    }

    /**
     * Метод для создания новой статистики
     * @param rule правила
     * @return возвращает новую статистику
     */
    private StatisticRule createNewStatic(Rule rule) {
        StatisticRule stat = new StatisticRule();
        stat.setRule(rule);
        stat.setCount(1L);
        return stat;
    }

    /**
     * Метод для получения количества вызовов правил
     * @return возвращает количество вызовов по каждому правилу
     */
    public List<StatisticRule> getAllStat() {
        return statisticRuleRepository.findAll();
    }
}
