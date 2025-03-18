package TeamWork.project.rules;

import TeamWork.project.dto.Recommendation;

import java.util.Optional;
import java.util.UUID;


public interface RecommendationRuleSet   {

    Optional<Recommendation> perform (UUID userId);
}
