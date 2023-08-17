package kg.mega.remoteTgBot.services;

import kg.mega.remoteTgBot.models.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageService {
    Message saveInDB(Update update);
}
