package TeamWork.project;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.rules.RecommendationRuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecommendationRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RecommendationRepository recommendationRepository;

    private UUID userId;
    private ProductType productType;
    private TransactionType transactionType;

    @BeforeEach
    public void setUp() {
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);

        userId = UUID.randomUUID();
        productType = ProductType.DEBIT;
        transactionType = TransactionType.DEPOSIT;
        recommendationRepository = new RecommendationRepository(jdbcTemplate);
    }

    @Test
    public void testIsUserOfReturnsTrue() {
        String sql = "SELECT EXISTS(SELECT 1 " +
                "FROM transactions WHERE user_id = ? " +
                " type IN (SELECT id FROM products WHERE type = ?))";
        when(jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class))
                .thenReturn(1);

        Boolean result = recommendationRepository.isUserOf(userId, productType);

        assertTrue(result);
        verify(jdbcTemplate).queryForObject(any(String.class), any(Object[].class), eq(Integer.class));
    }

    @Test
    public void testIsUserOfReturnsFalse() {
        String sql = "SELECT EXISTS(SELECT 1 " +
                "FROM transactions WHERE user_id = ? " +
                " type IN (SELECT id FROM products WHERE type = ?))";
        when(jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class))
                .thenReturn(0);
        Boolean result = recommendationRepository.isUserOf(userId, productType);

        assertFalse(result);
        verify(jdbcTemplate).queryForObject(any(String.class), any(Object[].class), eq(Integer.class));
    }

    @Test
    public void testSumReturnsNull() {
        String sql = "Select SUM(amount) " +
                "From transactions t " +
                "LEFT JOIN products p ON p.id = t.product_id " +
                "WHERE t.user_id =? AND p.type =? AND t.type=?";
        when(jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class))
                .thenReturn(null);

        int totalAmount = recommendationRepository.sum(userId, productType, transactionType);

        assertEquals(0, totalAmount);
    }

    @Test
    public void testNumberOfTransactionsReturnsTrue() {
        String sql = "Select count(t.*) " +
                "From transactions t " +
                "LEFT JOIN products p ON p.id = t.product_id " +
                "WHERE t.user_id =? AND p.type =?";
        when(jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class))
                .thenReturn(6);

        boolean result = recommendationRepository.numberOfTransactions(userId, productType);

        assertTrue(result);
    }

    @Test
    public void testNumberOfTransactionsReturnsFalse() {
        String sql = "Select count(t.*) " +
                "From transactions t " +
                "LEFT JOIN products p ON p.id = t.product_id " +
                "WHERE t.user_id =? AND p.type =?";
        when(jdbcTemplate.queryForObject(sql, new Object[]{userId, productType.name()}, Integer.class))
                .thenReturn(5);

        boolean result = recommendationRepository.numberOfTransactions(userId, productType);

        assertFalse(result);
    }
}
