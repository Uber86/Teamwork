package TeamWork.project.service;

import TeamWork.project.model.Rule;
import TeamWork.project.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuleRecommendationService {
    static int countAdd = 0;
    static int countDeleteRule = 0;
    static int countGetAllRules = 0;

    @Autowired
    private RuleRepository ruleRepository;

    public Rule addRule(Rule rule) {
        Rule save = ruleRepository.save(rule);
        countAdd++;
        return save;
    }

    public void deleteRule(UUID productId) {
        Rule rule = ruleRepository.findByProductId(productId);
        if (rule != null) {
            ruleRepository.delete(rule);
            countDeleteRule++;
        }
    }

    public List<Rule> getAllRules() {
        List<Rule> all = ruleRepository.findAll();
        countGetAllRules++;
        return all;
    }
}
