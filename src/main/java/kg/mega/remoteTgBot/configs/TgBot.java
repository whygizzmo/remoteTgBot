package kg.mega.remoteTgBot.configs;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Component
public class TgBot extends TelegramLongPollingBot {
    BotConfig botConfig;

    @Override
    public void onUpdateReceived(Update update) {
        // update.getMessage().getFrom().getId();       айди и юзернейм чтобы не забыть откуда брать
        //update.getMessage().getFrom().getUserName();
    }

    @Override
    public String getBotUsername() {
        return botConfig.botName;
    }

    @Override
    public String getBotToken() {
        return botConfig.botToken;
    }
}
