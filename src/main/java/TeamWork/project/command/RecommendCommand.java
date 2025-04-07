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
/**
 * Класс RecommendCommand реализует интерфейс TelegramCommand и обрабатывает команду /recommend username
 * username - пользователь банка (указывается при регистрации).
 * Он выдает рекомендации подходящии тому или иному пользователю.
 */
@Component
public class RecommendCommand  implements TelegramCommand{

    private final Pattern pattern = Pattern.compile("^/recommend\\s+([\\w.]+)$");

    private final RecommendationRepository recommendationRepository;

    private final RecommendationService recommendationService;

    public RecommendCommand(RecommendationRepository recommendationRepository, RecommendationService recommendationService) {
        this.recommendationRepository = recommendationRepository;
        this.recommendationService = recommendationService;
    }

    /**
     * Проверяет, поддерживает ли команда данный update.
     *
     * @param update объект Update, содержащий информацию о входящем сообщении
     * @return true, если команда поддерживается
     * (если команда равна /recommend username), иначе false
     */
    @Override
    public boolean support(Update update) {
        Optional<String> text = text(update);
        return text
                .map(it -> it.matches(pattern.pattern()))
                .orElse(false);
    }

    /**
     * Метод выдает динамическое рекомендации.
     * Обрабатывает команду /recommend username
     *
     * @param update объект Update, содержащий информацию о входящем сообщении
     * @return объект SendMessage выдает динамическую рекомендацию
     */
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
        String text = "Здравствуйте, " +
                recommendationRepository.getUserFirstAndLastName(userId) +
                " !\n" +
                "Ваши рекомендации: "+ recommendationService.getRecommendation(userId);
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format );
    }

}
