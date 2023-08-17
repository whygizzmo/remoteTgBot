package kg.mega.remoteTgBot.services;

import kg.mega.remoteTgBot.models.Employee;
import kg.mega.remoteTgBot.models.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageService {
    Message saveInDB(Update update);
    void saveInFile(Employee employee, Message message);
}
