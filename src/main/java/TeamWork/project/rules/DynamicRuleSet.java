package TeamWork.project.rules;

import TeamWork.project.dto.Querys;
import TeamWork.project.dto.Recommendation;
import TeamWork.project.model.Query;
import TeamWork.project.model.Rule;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;
import TeamWork.project.rules.querys.AbstractQuery;
import TeamWork.project.rules.querys.QueryFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static TeamWork.project.dto.Querys.*;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Component
public class DynamicRuleSet implements RecommendationRuleSet {

    private final RuleRepository repository;
    private final RecommendationRepository recommendationRepository;

    public DynamicRuleSet(RuleRepository repository, RecommendationRepository recommendationRepository) {
        this.repository = repository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        return repository.findAll().stream()
                .map(rule -> processRule(rule, userId))
                .filter(rule -> {
                    if (rule.equals("Простой кредит")) {
                        Optional.of(List.of(USER_OF,
                                TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW,
                                TRANSACTION_SUM_COMPARE));
                    } else if (rule.equals("Invest 500")) {
                        Optional.of(List.of(ACTIVE_USER_OF,
                                TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW,
                                TRANSACTION_SUM_COMPARE));
                    } else if (rule.equals("Top Saving")) {
                        Optional.of(List.of(USER_OF,
                                ACTIVE_USER_OF,
                                TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW,
                                TRANSACTION_SUM_COMPARE));
                    }
                    return false;
                }).collect(Optional.of());
    };
    private Optional<Recommendation> processRule(Rule rule, UUID userId) {
        Boolean reduce = rule.getQueries().stream()
                .map(query -> QueryFactory.from(query.getQuery(),
                        query.getArguments(),
                        query.isNegate()))
                .map(abstractQuery -> abstractQuery
                        .perform(userId, recommendationRepository))
                .reduce(true, (a, b) -> a && b);
        if (reduce){
            return Optional.of(new Recommendation(rule.getProductName()
                    ,rule.getProductId(),rule.getProductText()));
        }else {
            return Optional.empty();
        }
    }



}
//   Boolean bool = new AbstractQuery(true).perform(userId, DEBIT);
//
//    Object obj = new AbstractQuery(true) {
//            @Override
//            protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
//                return false;
//            }
//        }.perform(userId, DEBIT);

//    List<Rule> rule = repository.findAll();
//    boolean query = new Query(1L, Querys.USER_OF, List.of("DEBIT"), true, new Rule(1L,
//            "Простой кредит",
//            UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a"),
//            """
//                    Откройте мир выгодных кредитов с нами!
//
//                    Ищете способ быстро и без лишних хлопот получить нужную
//                    сумму? Тогда наш выгодный кредит — именно то, что вам нужно!
//                    Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный
//                    подход к каждому клиенту.
//
//                    Почему выбирают нас:
//
//                    Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому
//                    процесс рассмотрения заявки занимает всего несколько часов.
//
//                    Удобное оформление. Подать заявку на кредит можно онлайн на нашем
//                    сайте или в мобильном приложении.
//
//                    Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные
//                    цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.
//
//                    Не упустите возможность воспользоваться выгодными условиями кредитования
//                    от нашей компании!
//                    """, );
//    List<Query> lists = List.of(
//            new Query(1L, Querys.USER_OF, List.of("DEBIT") , true, 1L),
//            new Query(2L, TRANSACTION_SUM_COMPARE, List.of("DEBIT",">") , true, 1L),
//            new Query(3L, TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW, List.of("DEBIT",">","100000")  ,
//                    true, 1L));
//    Rule rules = new Rule(1L,
//            "Простой кредит",
//            UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a"),
//            """
//                    Откройте мир выгодных кредитов с нами!
//
//                    Ищете способ быстро и без лишних хлопот получить нужную
//                    сумму? Тогда наш выгодный кредит — именно то, что вам нужно!
//                    Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный
//                    подход к каждому клиенту.
//
//                    Почему выбирают нас:
//
//                    Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому
//                    процесс рассмотрения заявки занимает всего несколько часов.
//
//                    Удобное оформление. Подать заявку на кредит можно онлайн на нашем
//                    сайте или в мобильном приложении.
//
//                    Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные
//                    цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.
//
//                    Не упустите возможность воспользоваться выгодными условиями кредитования
//                    от нашей компании!
//                    """, perform(userId)
//    );
//        new AbstractQuery(true) {
//        @Override
//        protected boolean internalPerform(UUID userId, RecommendationRepository repository) {
//            return true;
//        }
//    }.perform(userId, (RecommendationRepository) list);

}
