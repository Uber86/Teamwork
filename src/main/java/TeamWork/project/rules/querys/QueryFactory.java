package TeamWork.project.rules.querys;

import TeamWork.project.dto.Querys;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;


public class QueryFactory {

    private static RuleRepository ruleRepository;


    public static AbstractQuery from(Querys query, String arguments, boolean negate) {
        switch (query){
            case USER_OF:
                return new UserOf();
            case ACTIVE_USER_OF:
                return new ActiveUserOf();
            case TRANSACTION_SUM_COMPARE:
                return new TransactionSumCompare();
            case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW:
                return new TransactionSumCompareDepositWithdraw();
        }
        return null;
    }


}

