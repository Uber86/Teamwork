package TeamWork.project.controller;

import TeamWork.project.model.Rule;
import TeamWork.project.model.StatisticRule;
import TeamWork.project.service.RuleRecommendationService;
import TeamWork.project.service.StatisticRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rule")
public class RuleRecommendationController {

    @Autowired
    private RuleRecommendationService recommendationService;

    @Autowired
    private StatisticRuleService statisticRuleService;


    @PostMapping
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        Rule createdRule = recommendationService.addRule(rule);
        return new ResponseEntity<>(createdRule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rule>> getAllRules() {
        List<Rule> rules = recommendationService.getAllRules();
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteRule(@PathVariable UUID productId) {
        recommendationService.deleteRule(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/stats")
    public List<StatisticRule> getStats() {
        List<StatisticRule> rules = statisticRuleService.getAllStat();
        return new ResponseEntity<>(rules, HttpStatus.OK).getBody();
    }
}
