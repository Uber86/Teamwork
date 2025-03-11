package TeamWork.project.repository;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RecommendationRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Boolean isUserOf(UUID userId, ProductType productType){
        return false;
    }
    public int sum (UUID userId, ProductType productType, TransactionType transactionType){
        return 0;
    }
}
