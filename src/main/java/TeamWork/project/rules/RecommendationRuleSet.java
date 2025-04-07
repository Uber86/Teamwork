package TeamWork.project.rules;

import TeamWork.project.dto.Recommendation;

import java.util.Optional;
import java.util.UUID;

/**
 * Класс-интерфейс RecommendationRuleSet
 * Метод содержащийся в классе для реализации наследниками RecommendationRuleSet
 */
public interface RecommendationRuleSet   {

    Optional<Recommendation> perform (UUID userId);
}
