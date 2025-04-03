package TeamWork.project.command;

import TeamWork.project.utils.CommandSupportUtils;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import static TeamWork.project.utils.CommandSupportUtils.chatId;
/**
 * Класс StartCommand реализует интерфейс TelegramCommand и обрабатывает команду /start.
 * Он отвечает пользователю приветственным сообщением.
 */
@Component
public class StartCommand implements TelegramCommand{

    private static final String START = "/start";
    /**
     * Проверяет, поддерживает ли команда данный update.
     *
     * @param update объект Update, содержащий информацию о входящем сообщении
     * @return true, если команда поддерживается (если команда равна /start), иначе false
     */
    @Override
    public boolean support(Update update) {
        return CommandSupportUtils.isStringEqualsCommand(update, START);
    }

    /**
     * Обрабатывает команду /start и формирует ответное сообщение для пользователя.
     *
     * @param update объект Update, содержащий информацию о входящем сообщении
     * @return объект SendMessage с приветственным сообщением и инструкциями
     */
    @Override
    public SendMessage handle(Update update) {
        String notificationTask = update.message().chat().username();
        String text = "Здравствуйте, " + update.message().from().username() + " !\n" +
                "Для получения рекомендации напишите /recommend username"+"\n" +
                "Где username - это username в банке.";
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format);
    }


}