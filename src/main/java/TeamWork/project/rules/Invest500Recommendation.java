package TeamWork.project.rules;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.Recommendation;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class Invest500Recommendation implements RecommendationRuleSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationRuleSet.class);

    private final RecommendationRepository recommendationRepository;

    public Invest500Recommendation(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        Boolean userOf = recommendationRepository.isUserOf(userId, ProductType.DEBIT)&&
                !recommendationRepository.isUserOf(userId,ProductType.INVEST)&&
                recommendationRepository.sum(userId,ProductType.SAVING, TransactionType.DEPOSIT)>1000;
        return null;
    }
}
