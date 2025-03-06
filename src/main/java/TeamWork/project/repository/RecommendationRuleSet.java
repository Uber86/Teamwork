package TeamWork.project.repository;

public interface RecommendationRuleSet extends JdbcTemplate<Model, Long>{
    String findById(long id);
}
