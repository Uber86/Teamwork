package TeamWork.project.service;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.rules.RecommendationRuleSet;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс-сервис RecommendationService
 * работает с классами помеченных аннотацией @Component
 */
@Service
public class RecommendationService {

    private final List<RecommendationRuleSet> ruleSets;


    public RecommendationService(List<RecommendationRuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    /**
     * Метод для работы с рекомендациями
     * @param userId уникальный идентификатор user
     * @return возвращает подходящие рекомендации
     */
    public List<Recommendation> getRecommendation(UUID userId){
        return ruleSets.stream()
                .map(recommendationRuleSet -> recommendationRuleSet.perform(userId))
                .filter(Optional::isPresent)
                .map(it -> it.get())
                .toList();
    }
}
