package TeamWork.project.repository;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Класс-репозитории RecommendationRepository
 * содержит методы для рекомендации
 */
@Repository
public class RecommendationRepository {
    private final JdbcTemplate jdbcTemplate;

    private final Cache<UUID, Boolean> userProductCache;
    private final Cache<UUID, Integer> transactionSumCache;
    private final Cache<UUID, Integer> transactionCountCache;


    public RecommendationRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate,
                                    Cache<UUID, Boolean> userProductCache,
                                    Cache<UUID, Integer> transactionSumCache,
                                    Cache<UUID, Integer> transactionCountCache) {
        this.jdbcTemplate = jdbcTemplate;

        this.userProductCache = userProductCache;
        this.transactionSumCache = transactionSumCache;
        this.transactionCountCache = transactionCountCache;
    }

    /**
     * Метод о подтверждении у user продукта
     * @param userId уникальный идентификатор user
     * @param productType продукт пользователя
     * @return true если у пользователя имеется продукт
     * false при отсутствии
     */
    public Boolean isUserOf(UUID userId, ProductType productType) {
        return userProductCache.get(userId, key -> {
            String sql = "SELECT EXISTS(SELECT 1 " +
                    "FROM transactions WHERE user_id = ? " +
                    "AND product_id IN (SELECT id FROM products WHERE type = ?))";
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class);
            return count != null && count > 0;
        });
    }

    /**
     * Метод суммы транзакции у user
     * @param userId уникальный идентификатор user
     * @param productType продукт пользователя
     * @param transactionType транзакции пользователя
     * @return сумма по транзакциям продукта
     */
    public int sum(UUID userId, ProductType productType, TransactionType transactionType) {
        return transactionSumCache.get(userId, key -> {
            String sql = "SELECT SUM(amount) " +
                    "FROM transactions t " +
                    "LEFT JOIN products p ON p.id = t.product_id " +
                    "WHERE t.user_id =? AND p.type=? AND t.type=?";
            Integer totalAmount = jdbcTemplate.queryForObject(sql,
                    new Object[]{userId, productType.name(), transactionType.name()}, Integer.class);
            return totalAmount != null ? totalAmount : 0;
        });
    }

    /**
     * Метод для сравнения количества транзакции по продуктам
     * @param userId уникальный идентификатор user
     * @param productType продукт пользователя
     * @return true количество продуктов у пользователя >5 иначе false
     */
    public boolean numberOfTransactions(UUID userId, ProductType productType) {
        return transactionCountCache.get(userId, key -> {
            String sql = "SELECT COUNT(t.*) " +
                    "FROM transactions t " +
                    "LEFT JOIN products p ON p.id = t.product_id " +
                    "WHERE t.user_id =? AND p.type=?";
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class);
            return count != null ? count : 0;
        }) > 5;
    }

    /**
     * Метод для получения уникального идентификатора user
     * @param userName username пользователя учетной записи
     * @return уникальный идентификатор user
     */
    public UUID getUserId(String userName){
        String sql = "SELECT \"ID\" FROM \"USERS\" WHERE \"USERNAME\" =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName}, UUID.class);
    }

    /**
     * Метод для получения имени и фамилии пользователя
     * @param userId уникальный идентификатор user
     * @return имени и фамилии пользователя банка
     */
    public String getUserFirstAndLastName(UUID userId) {
        String sql = "Select first_name, last_name FROM users WHERE id =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
    }
}
