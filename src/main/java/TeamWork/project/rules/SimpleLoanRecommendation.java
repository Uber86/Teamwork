package TeamWork.project.rules;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.Recommendation;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class SimpleLoanRecommendation implements RecommendationRuleSet{

    public final RecommendationRepository repository;

    private static final Recommendation RECOMMENDATION = new Recommendation(
            "Простой кредит", UUID.fromString
            ("ab138afb-f3ba-4a93-b74f-0fcee86d447f"),
            """
            Откройте мир выгодных кредитов с нами!
                        
            Ищете способ быстро и без лишних хлопот получить нужную 
            сумму? Тогда наш выгодный кредит — именно то, что вам нужно! 
            Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный 
            подход к каждому клиенту.
                        
            Почему выбирают нас:
                        
            Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому 
            процесс рассмотрения заявки занимает всего несколько часов.
                        
            Удобное оформление. Подать заявку на кредит можно онлайн на нашем 
            сайте или в мобильном приложении.
                        
            Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные 
            цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.
                        
            Не упустите возможность воспользоваться выгодными условиями кредитования 
            от нашей компании!
            """);

    public SimpleLoanRecommendation(RecommendationRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Recommendation> perform(UUID userId) {
        Boolean userOf = !repository.isUserOf(userId, ProductType.CREDIT)&&
                (repository.sum(userId, ProductType.DEBIT, TransactionType.DEPOSIT)>
                        repository.sum(userId,ProductType.DEBIT, TransactionType.WITHDRAW))&&
                (repository.sum(userId,ProductType.DEBIT,TransactionType.WITHDRAW)>100_000);
        if (userOf){
            return Optional.of(RECOMMENDATION);
        }
        return Optional.empty();
    }
}
