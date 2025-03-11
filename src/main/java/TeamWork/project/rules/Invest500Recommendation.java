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

    private static final  Recommendation RECOMMENDATION = new Recommendation(UUID
            .fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a"),"""
            Откройте свой путь к успеху с индивидуальным инвестиционным счетом
            (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните
    инвестировать с умом. Пополните счет до конца года и получите выгоду в
    виде вычета на взнос в следующем налоговом периоде. Не упустите возможность
    разнообразить свой портфель, снизить риски и следить за актуальными рыночными
    тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!
            """, "Invest 500");

    private final RecommendationRepository recommendationRepository;

    public Invest500Recommendation(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        Boolean userOf = recommendationRepository.isUserOf(userId, ProductType.DEBIT)&&
                !recommendationRepository.isUserOf(userId,ProductType.INVEST)&&
                recommendationRepository.sum(userId,ProductType.SAVING, TransactionType.DEPOSIT)>1000;
        if (userOf){
            return Optional.of(RECOMMENDATION);
        }
        return Optional.empty();
    }
}
