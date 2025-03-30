package TeamWork.project.command;

import TeamWork.project.service.RecommendationService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static TeamWork.project.command.CommandSupportUtils.chatId;

@Component
public class RecommendCommand implements TelegramCommand{

    private static final String RECOMMEND = "/recommend";
    private RecommendationService recommendationService;
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean support(Update update){
        return CommandSupportUtils.isStringEqualsCommand(update, RECOMMEND);
    }

    @Override
    public SendMessage handle(Update update){
        String notificationTask = update.message().chat().username();
        String text = "Здравствуйте, " + update.message().from().username() + " !\n" +
                "Ваши рекомендации: " + recommendationService.getRecommendation(getUserId(update));
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format);
    }
    private UUID getUserId(Update update){
        String sql = "Select ID " +
                "From USERS " +
                "WHERE USERNAME =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{update.message().chat().username()}, UUID.class);
    }
}
