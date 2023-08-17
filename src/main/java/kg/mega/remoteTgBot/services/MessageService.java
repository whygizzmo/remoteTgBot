package kg.mega.remoteTgBot.services;

import kg.mega.remoteTgBot.models.entities.Employee;
import kg.mega.remoteTgBot.models.entities.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageService {
    Message saveInDB(Update update);
    void saveInFile(Employee employee, Message message);

    String getAllMessagesForMonthFromEmployee(long employeeId);
}
