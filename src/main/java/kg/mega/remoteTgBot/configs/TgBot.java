package kg.mega.remoteTgBot.configs;

import kg.mega.remoteTgBot.services.MessageService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Component
public class TgBot extends TelegramLongPollingBot {
    BotConfig botConfig;
   final MessageService messageService;


    public TgBot(BotConfig botConfig, MessageService messageService) {
        this.botConfig = botConfig;
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.getMessage().getText().contains("/удаленка")){
            messageService.saveInDB(update);
           // messageService.saveInFile
        }

//        System.out.println(update.getMessage().getFrom().getId());
//        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getUserName());
//        System.out.println(update.getMessage());
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
