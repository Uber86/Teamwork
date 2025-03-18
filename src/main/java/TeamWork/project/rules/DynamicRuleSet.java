package TeamWork.project.rules;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DynamicRuleSet implements RecommendationRuleSet {

    private final RuleRepository repository;

    public DynamicRuleSet(RuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        return Optional.empty();
    }

}
