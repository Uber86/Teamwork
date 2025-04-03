package TeamWork.project.utils;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import java.util.Optional;

public class CommandSupportUtils {

    public static boolean isStringEqualsCommand(Update update, String command) {
        return text(update)
                .map(command::equals)
                .orElse(false);
    }

    public static Optional<String> text(Update update) {
        return Optional.of(update)
                .map(Update::message)
                .map(Message::text);
    }

    public static Long chatId(Update update) {
        return Optional.of(update)
                .map(Update::message)
                .map(Message::chat)
                .map(Chat::id)
                .orElse(-1L);
    }
}
