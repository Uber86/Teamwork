package TeamWork.project.rules.querys;

import TeamWork.project.dto.ComparisonOperators;
import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;


import java.util.List;
import java.util.UUID;


public class TransactionSumCompare extends AbstractQuery {

    private final ProductType productType;

    private final ComparisonOperators comparisonType;


    private Integer number ;

    protected TransactionSumCompare(List <String> args,  boolean negate) {
        super(negate);
        this.productType = ProductType.valueOf(args.get(0));
        this.comparisonType = new ComparisonOperators(args.get(1));
        if( number <= 0){
            throw new IllegalArgumentException("Значение должно быть положительным");
        }
        this.number = Integer.parseInt(args.get(2));
    }

    @Override
    protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
        int sumTran = repository.sum(userId, productType , TransactionType.DEPOSIT );
        return comparisonType.comparison(sumTran, number);
    }
}
