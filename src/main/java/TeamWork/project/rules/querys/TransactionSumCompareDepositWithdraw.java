package TeamWork.project.rules.querys;

import TeamWork.project.dto.ComparisonOperators;
import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;


import java.util.List;
import java.util.UUID;


public class TransactionSumCompareDepositWithdraw extends AbstractQuery{

    private final ProductType productType;

    private final ComparisonOperators compatype;


    protected TransactionSumCompareDepositWithdraw(boolean negate, List<String> args) {
        super(negate);
        this.productType = ProductType.valueOf(args.get(0));
        this.compatype = new ComparisonOperators(args.get(1));
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        int sumD = repository.sum(userId, productType, TransactionType.DEPOSIT);
        int sumW = repository.sum(userId, productType, TransactionType.WITHDRAW);
        return compatype.comparison(sumD, sumW);
    }


}
