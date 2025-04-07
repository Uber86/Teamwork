package TeamWork.project.service;

import TeamWork.project.model.Rule;
import TeamWork.project.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Класс-сервис RuleRecommendationService
 * для работы с правилами
 * добавление, удаление и получения правил
 */
@Service
public class RuleRecommendationService {

    @Autowired
    private RuleRepository ruleRepository;


    public Rule addRule(Rule rule) {
        Rule save = ruleRepository.save(rule);
        return save;
    }

    public void deleteRule(UUID productId) {
        Rule rule = ruleRepository.findByProductId(productId);
        if (rule != null) {
            ruleRepository.delete(rule);
        }
    }

    public List<Rule> getAllRules() {
        List<Rule> all = ruleRepository.findAll();
        return all;
    }
}
