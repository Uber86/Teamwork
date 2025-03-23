package TeamWork.project.rules.querys;

import TeamWork.project.repository.RecommendationRepository;


import java.util.UUID;


public class UserOf extends AbstractQuery{

    protected UserOf(boolean negate) {
        super(negate);
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        return false;
    }

}
