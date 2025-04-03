package TeamWork.project.rules;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.model.Rule;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;
import TeamWork.project.rules.querys.QueryFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DynamicRuleSet implements RecommendationRuleSet {

    private final RuleRepository repository;
    private final RecommendationRepository recommendationRepository;

    public DynamicRuleSet(RuleRepository repository, RecommendationRepository recommendationRepository) {
        this.repository = repository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Optional<Recommendation>
    perform(UUID userId) {
        return repository.findAll().stream()
                .flatMap(rule -> processRule(rule, userId).stream())
                .findFirst();
    }


    private Optional<Recommendation> processRule(Rule rule, UUID userId) {
        Boolean reduce = rule.getQueries().stream()
                .map(query -> QueryFactory.from(query.getQuery(),
                        query.getArguments(),
                        query.isNegate()))
                .map(abstractQuery -> abstractQuery
                        .perform(userId, recommendationRepository))
                .reduce(true, (a, b) -> a && b);
        if (reduce){
            return Optional.of(new Recommendation(rule.getProductName()
                    ,rule.getProductId(),rule.getProductText()));
        }else {
            return Optional.empty();
        }
    }
}



