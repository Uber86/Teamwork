package TeamWork.project.repository;

import TeamWork.project.model.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RecommendationRuleSet   {

    Optional<Model> findAll ();
}
