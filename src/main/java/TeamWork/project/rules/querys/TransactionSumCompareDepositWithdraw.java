package TeamWork.project.rules.querys;

import TeamWork.project.dto.ComOper;
import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


public class TransactionSumCompareDepositWithdraw extends AbstractQuery{

    private final ProductType productType;

    private final ComOper compatype;


    protected TransactionSumCompareDepositWithdraw(boolean negate, List<String> args) {
        super(negate);
        this.productType = ProductType.valueOf(args.get(0));
        this.compatype = new ComOper(args.get(1));
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        int sumD = repository.sum(userId, productType, TransactionType.DEPOSIT);
        int sumW = repository.sum(userId, productType, TransactionType.WITHDRAW);
        return compatype.compair(sumD, sumW);
    }


}
