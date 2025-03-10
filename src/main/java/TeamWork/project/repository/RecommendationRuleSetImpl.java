package TeamWork.project.repository;

import TeamWork.project.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecommendationRuleSetImpl implements RecommendationRuleSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationRuleSet.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecommendationRuleSetImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Model> findByClientId(Long clientId) {
        return Optional.empty();
    }
}
