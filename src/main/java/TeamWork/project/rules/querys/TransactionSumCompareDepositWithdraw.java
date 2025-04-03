package TeamWork.project.rules.querys;

import TeamWork.project.dto.ComparisonOperators;
import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;


import java.util.List;
import java.util.UUID;


public class TransactionSumCompareDepositWithdraw extends AbstractQuery{

    private final ProductType productType;

    private final ComparisonOperators comparisonType;


    protected TransactionSumCompareDepositWithdraw( List<String> args,  boolean negate) {
        super(negate);
        this.productType = ProductType.valueOf(args.get(0));
        this.comparisonType = new ComparisonOperators(args.get(1));
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        int sumD = repository.sum(userId, productType, TransactionType.DEPOSIT);
        int sumW = repository.sum(userId, productType, TransactionType.WITHDRAW);
        return comparisonType.comparison(sumD, sumW);
    }
}
