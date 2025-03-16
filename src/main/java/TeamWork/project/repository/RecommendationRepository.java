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

    public Boolean isUserOf(UUID userId, ProductType productType) {
        //String sql = "SELECT COUNT(*) FROM products  WHERE id = ? AND type = ?";
        String sql = "SELECT EXISTS(SELECT 1 FROM transactions WHERE user_id = ? AND type IN (SELECT id FROM products WHERE type = ?))";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class);
        return count != null && count > 0;
    }

    public int sum(UUID userId, ProductType productType, TransactionType transactionType) {
        //String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND product_id = ? AND type = ?";
        String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND product_id = ? AND type IN (SELECT id FROM products WHERE type = ?  )";
        Integer totalAmount = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name(), transactionType.name()}, Integer.class);

        return totalAmount != null ? totalAmount : 0;
    }
}
