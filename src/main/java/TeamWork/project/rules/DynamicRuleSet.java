package TeamWork.project.rules;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.Querys;
import TeamWork.project.dto.Recommendation;
import TeamWork.project.model.Query;
import TeamWork.project.model.Rule;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;
import TeamWork.project.rules.querys.*;
import jakarta.persistence.EnumType;
import org.hibernate.mapping.UserDefinedObjectType;
import org.springframework.stereotype.Component;


import java.lang.constant.ClassDesc;
import java.util.*;
import java.util.stream.Collectors;

import static TeamWork.project.dto.ProductType.DEBIT;
import static TeamWork.project.dto.Querys.*;


@Component
public class DynamicRuleSet  implements RecommendationRuleSet {


    private final RuleRepository repository;



    public DynamicRuleSet(RuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        List<Rule> rule = repository.findAll();

        return Optional.empty();
    }

}
