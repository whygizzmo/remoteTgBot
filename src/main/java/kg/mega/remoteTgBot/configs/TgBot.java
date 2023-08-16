package kg.mega.remoteTgBot.configs;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Component
public class TgBot extends TelegramLongPollingBot {
    BotConfig botConfig;


    public TgBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getId());
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getUserName());
        System.out.println(update.getMessage());
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
