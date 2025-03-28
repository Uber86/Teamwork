package TeamWork.project.service;

import TeamWork.project.model.Rule;
import TeamWork.project.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class RuleRecommendationService {
    @Autowired
    private RuleRepository ruleRepository;

    public Rule addRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    public void deleteRule(UUID productId) {
        Rule rule = ruleRepository.findByProductId(productId);
        if (rule != null) {
            ruleRepository.delete(rule);
        }
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
