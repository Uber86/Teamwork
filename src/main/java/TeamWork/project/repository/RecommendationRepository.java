package TeamWork.project.repository;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

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

    /*
    Метод о подтверждении у юзера продукта
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

    /*
    Метод суммы транзакции у юзера
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

    /*
    Метод для сравнения количества транзакции по продуктам
    должен быть >5
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

    /*
    /*
    Метод для сравнения суммы транзакции определенного типа с константой
     */
//    public boolean transactionSumCompare(UUID userId, ProductType productType,
//                                         TransactionType transactionType,
//                                         ComparisonOperators operators, int sum
//    ) {
//        String sql = "Select SUM(amount) " +
//                "From transactions t " +
//                "LEFT JOIN products p ON p.id = t.product_id " +
//                "WHERE t.user_id =? AND p.type =? AND t.type=?";
//        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId,
//                productType.name(), transactionType.name()}, Integer.class);
//        Boolean a = Boolean.valueOf(count + operators.toString() + sum);
//        return a;
//    }







}
