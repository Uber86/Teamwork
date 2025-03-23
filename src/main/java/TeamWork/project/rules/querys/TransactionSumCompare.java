package TeamWork.project.rules.querys;

import TeamWork.project.repository.RecommendationRepository;


import java.util.UUID;


public class TransactionSumCompare extends AbstractQuery {

    protected TransactionSumCompare(boolean negate) {
        super(negate);
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        return false;
    }
}
