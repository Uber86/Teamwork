package TeamWork.project.repository;

import TeamWork.project.dto.ComparisonOperators;
import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.Querys;
import TeamWork.project.dto.TransactionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.UUID;

@Repository
public class RecommendationRepository {
    private final JdbcTemplate jdbcTemplate;


    public RecommendationRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    Метод о подтверждении у юзера продукта
     */
    public Boolean isUserOf(UUID userId, ProductType productType) {
        //String sql = "SELECT COUNT(*) FROM products  WHERE id = ? AND type = ?";
        //"LEFT JOIN products p ON p.id = t.product_id WHERE t.user_id =? AND p.type =?"
        String sql = "SELECT EXISTS(SELECT 1 " +
                "FROM transactions WHERE user_id = ? " +
                " type IN (SELECT id FROM products WHERE type = ?))";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class);
        return count != null && count > 0;
    }

    /*
    Метод суммы транзакции у юзера
     */
    public int sum(UUID userId, ProductType productType, TransactionType transactionType) {
        //String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND product_id = ? AND type = ?";
        //"LEFT JOIN products p ON p.id = t.product_id WHERE t.user_id =? AND p.type =? AND t.type=?"
        String sql = "SELECT SUM(amount) " +
                "FROM transactions WHERE user_id = ? AND product_id = ? " +
                "AND type IN (SELECT id FROM products WHERE type = ?  )";
        Integer totalAmount = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name(), transactionType.name()}, Integer.class);

        return totalAmount != null ? totalAmount : 0;
    }

    /*
    Метод для сравнения количества транзакции по продуктам
    должен быть >5
     */
    public boolean numberOfTransactions(UUID userId, ProductType productType ) {
        String sql = "Select t.user_id, p.id " +
                "From transactions t " +
                "LEFT JOIN products p ON p.id = t.product_id " +
                "WHERE t.user_id =? AND p.type =?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class);
        return count > 5;
    }

    /*
    Метод для сравнения суммы транзакции определенного типа с константой
     */
    public boolean TransactionSumCompare(UUID userId, ProductType productType,
                                         TransactionType transactionType,
                                         ComparisonOperators operators, int sum
    ) {
        String sql = "Select SUM(amount) " +
                "From transactions t " +
                "LEFT JOIN products p ON p.id = t.product_id " +
                "WHERE t.user_id =? AND p.type =? AND t.type=?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name(), transactionType.name()}, Integer.class);
        Boolean a = Boolean.valueOf(count + operators.name() + sum);
        return a;
    }

    public boolean TransactionSumCompareDepositWithdraw(UUID userId, ProductType productType,
                                         ComparisonOperators operators
    ) {
        String sqlDeposit = "SELECT SUM(amount) " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id =? AND p.type =? AND t.type = DEPOSIT )";

        String sqlWithdraw = "SELECT SUM(amount) " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id =? AND p.type =? AND t.type = DEPOSIT )";
        Boolean a =
    }




}
