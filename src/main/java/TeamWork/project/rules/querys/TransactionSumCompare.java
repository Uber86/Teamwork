package TeamWork.project.rules.querys;

import TeamWork.project.repository.RecommendationRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionSumCompare extends AbstractQuery {

    protected TransactionSumCompare(boolean negate) {
        super(negate);
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        return false;
    }
}
