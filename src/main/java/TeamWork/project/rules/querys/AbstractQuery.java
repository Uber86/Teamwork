package TeamWork.project.rules.querys;

import TeamWork.project.repository.RecommendationRepository;

import java.util.UUID;

public abstract class AbstractQuery {
    private final boolean negate;

    protected AbstractQuery(boolean negate) {
        this.negate = negate;
    }

    public Boolean perform(UUID userId, RecommendationRepository repository) {
        return negate != internalPerform(userId, repository);
    }

    protected abstract boolean internalPerform(UUID userId, RecommendationRepository repository) ;
}
