package TeamWork.project.repository;

import TeamWork.project.model.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRuleSet extends JdbcTemplate<Model, Long> {

    Long findById(long id);
}
