package TeamWork.project.rules.querys;

import TeamWork.project.dto.ProductType;
import TeamWork.project.repository.RecommendationRepository;


import java.util.List;
import java.util.UUID;


public class UserOf extends AbstractQuery{

    private final ProductType productType;

    protected UserOf(List<String> args, boolean negate) {
        super(negate);
        this.productType = ProductType.valueOf(args.get(0));
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        return repository.isUserOf(userId, productType);
    }
}
