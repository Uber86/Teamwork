package TeamWork.project.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import static TeamWork.project.command.CommandSupportUtils.chatId;

@Component
public class RecommendCommand implements TelegramCommand{
    private static final String RECOMMEND = "/recommend";
    @Override
    public boolean support(Update update){
        return CommandSupportUtils.isStringEqualsCommand(update, RECOMMEND);
    }

    @Override
    public SendMessage handle(Update update){
        String notificationTask = update.message().chat().username();
        String text = "Здравствуйте, " + update.message().from().username() + " !";
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format);
    }
}
