package TeamWork.project.repository;

import TeamWork.project.model.Model;

import java.util.Optional;

public class RecommendationRuleSetImpl implements RecommendationRuleSet {

    @Override
    public Optional<Model> findByClientId(Long clientId) {
        return Optional.empty();
    }
}
