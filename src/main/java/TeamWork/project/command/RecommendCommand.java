package TeamWork.project.command;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.model.Rule;
import TeamWork.project.repository.RecommendationRepository;
import TeamWork.project.repository.RuleRepository;
import TeamWork.project.rules.DynamicRuleSet;
import TeamWork.project.rules.RecommendationRuleSet;
import TeamWork.project.rules.querys.QueryFactory;
import TeamWork.project.service.RecommendationService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static TeamWork.project.utils.CommandSupportUtils.chatId;
import static TeamWork.project.utils.CommandSupportUtils.text;

@Component
public class RecommendCommand extends DynamicRuleSet implements TelegramCommand{

    private final Pattern pattern = Pattern.compile("^/recommend\\s+([\\w.]+)$");

    private final RecommendationRepository recommendationRepository;

    public RecommendCommand(RuleRepository repository, RecommendationRepository recommendationRepository) {
        super(repository, recommendationRepository);
        this.recommendationRepository = recommendationRepository;
    }


    @Override
    public boolean support(Update update) {
        Optional<String> text = text(update);
        return text
                .map(it -> it.matches(pattern.pattern()))
                .orElse(false);
    }

//    @Override
//    public SendMessage handle(Update update){
//        String notificationTask = update.message().chat().username();
//        String text = "recommend founded";
//        String format = String.format(text, notificationTask);
//        return new SendMessage(chatId(update), format);
//    }

    @Override
    public SendMessage handle(Update update){
        Matcher matcher = pattern.matcher(update.message().text());
        if (!matcher.matches()) {
            return new SendMessage(chatId(update),
                    "Неверный формат команды. Используйте: /recommend <username>");
        }
        String user = matcher.group(1);
        UUID userId = recommendationRepository.getUserId(user);
        if (userId == null) {
            return new SendMessage(chatId(update), "Пользователь не найден.");
        }

        String notificationTask = update.message().chat().username();
        String text = "Здравствуйте, " + user + " !\n" +
                "Ваши рекомендации: "+ perform(userId);
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format );
    }


//    private UUID getUserId(String user){
//        String sql = "Select ID " +
//                "From USERS " +
//                "WHERE USERNAME =?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{user}, UUID.class);
//    }
    //@Override
    //public SendMessage handle(Update update) {
    //    Optional<String> text = text(update);
    //    if(text.isPresent()) {
    //        Matcher matcher = pattern.matcher(text.get());
    //        if(matcher.find()){
    //            String user = matcher.group(1);
    //            return new SendMessage(chatId(update), "Здравствуйте, " + user + " !\n");
    //        }
    //    }
    //    return new SendMessage(chatId(update), "Ошибка");
    //}
}
