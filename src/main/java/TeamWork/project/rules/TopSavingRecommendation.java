package TeamWork.project.rules;

import TeamWork.project.dto.ProductType;
import TeamWork.project.dto.Recommendation;
import TeamWork.project.dto.TransactionType;
import TeamWork.project.repository.RecommendationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TopSavingRecommendation implements RecommendationRuleSet {

    public final RecommendationRepository repository;

    public final static Recommendation RECOMMENDATION = new Recommendation
            ( "Top Saving",UUID.fromString
            ("59efc529-2fff-41af-baff-90ccd7402925"), """
            Откройте свою собственную «Копилку» с нашим банком! 
            «Копилка» — это уникальный банковский инструмент, который поможет 
            вам легко и удобно накапливать деньги на важные цели. Больше никаких 
            забытых чеков и потерянных квитанций — всё под контролем!
                        
            Преимущества «Копилки»:
                        
            Накопление средств на конкретные цели. Установите лимит и срок накопления, 
            и банк будет автоматически переводить определенную сумму на ваш счет.
                        
            Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте 
            процесс накопления и корректируйте стратегию при необходимости.
                        
            Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ 
            к ним возможен только через мобильное приложение или интернет-банкинг.
                        
            Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!
            """);

    public TopSavingRecommendation(RecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Recommendation> perform(UUID userId) {
        Boolean userOf = repository.isUserOf(userId, ProductType.DEBIT)&&
                (repository.sum(userId, ProductType.DEBIT, TransactionType.DEPOSIT)>=
                        50000 || repository.sum(userId, ProductType.SAVING, TransactionType.DEPOSIT)>=
                        50000)&& (repository.sum(userId, ProductType.DEBIT, TransactionType.DEPOSIT)>
                repository.sum(userId,ProductType.DEBIT,TransactionType.WITHDRAW));
        if (userOf){
            return Optional.of(RECOMMENDATION);
        }
        return Optional.empty();
    }
}
