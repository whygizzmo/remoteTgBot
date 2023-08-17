package kg.mega.remoteTgBot.configs;

import kg.mega.remoteTgBot.models.enums.BotStatus;
import kg.mega.remoteTgBot.services.EmployeeService;
import kg.mega.remoteTgBot.services.MessageService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Data
@Component
public class TgBot extends TelegramLongPollingBot {
    BotConfig botConfig;
    final MessageService messageService;
    final EmployeeService employeeService;

    private BotStatus botStatus = BotStatus.NORMAL;


    public TgBot(BotConfig botConfig, MessageService messageService, EmployeeService employeeService) {
        this.botConfig = botConfig;
        this.messageService = messageService;
        this.employeeService = employeeService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message;
        String textMessage = null;
        long chatId = 0;

        if (update.hasMessage() && update.getMessage().hasText()) {
            message = update.getMessage();
            textMessage = message.getText();
            chatId = message.getChatId();
        }


        if (textMessage.contains("/list")) {
            sendReply(chatId, employeeService.getListEmployee());

            botStatus=BotStatus.WAITING_FOR_NUMBER;
        }


        if (botStatus == BotStatus.WAITING_FOR_NUMBER) {
            long idEmployee = Integer.parseInt(textMessage);
            sendReply(chatId,messageService.getAllMessagesForMonthFromEmployee(idEmployee));

            botStatus = BotStatus.NORMAL;

        }


        if (textMessage.contains("/удаленка")) {
            messageService.saveInDB(update);

        }


    }

    private void sendReply(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
