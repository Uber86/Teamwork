package TeamWork.project.repository;

import TeamWork.project.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAll();

    Rule findByProductId(UUID productId);
}
