package TeamWork.project.rules.querys;

import TeamWork.project.dto.Querys;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;

import java.util.Collections;

/**
 * Класс QueryFactory
 * для работы вне пакета querys с методами абстрактных классов
 */
public class QueryFactory {

    private static RuleRepository ruleRepository;


    /**
     * Метод статический для динамического подбора рекомендации для пользователя
     * @return возвращает подходящий для пользователя рекомендации
     * иначе null если пользователь не подходит не под одно из рекомендации
     */
    public static AbstractQuery from(Querys query, String arguments, boolean negate) {
        switch (query){
            case USER_OF:
                return new  UserOf(Collections.singletonList(arguments), negate);
            case ACTIVE_USER_OF:
                return new ActiveUserOf(Collections.singletonList(arguments), negate);
            case TRANSACTION_SUM_COMPARE:
                return new TransactionSumCompare(Collections.singletonList(arguments), negate);
            case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW:
                return new TransactionSumCompareDepositWithdraw(Collections
                        .singletonList(arguments), negate);
            default:
                return null;
        }
    }
}

