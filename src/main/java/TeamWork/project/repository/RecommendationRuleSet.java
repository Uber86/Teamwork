package TeamWork.project.repository;

public interface RecommendationRuleSet extends JdbcTemplate<Model, Long>{
    Long findById(long id);
}
