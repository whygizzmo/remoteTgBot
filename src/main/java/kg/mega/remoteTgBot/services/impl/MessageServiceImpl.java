package kg.mega.remoteTgBot.services.impl;

import kg.mega.remoteTgBot.models.Employee;
import kg.mega.remoteTgBot.models.Message;
import kg.mega.remoteTgBot.repos.EmployeeRepository;
import kg.mega.remoteTgBot.repos.MessageRepository;
import kg.mega.remoteTgBot.services.MessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;
    final EmployeeRepository employeeRepository;

    public MessageServiceImpl(MessageRepository messageRepository, EmployeeRepository employeeRepository) {
        this.messageRepository = messageRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Message checkRemoteMessage(Update update) {
        Message message = new Message();
        Employee employee = employeeRepository.findByUserName(update.getMessage().getFrom().getUserName());
        if (employee == null){
            employee = employeeRepository.save(new Employee(update.getMessage().getFrom().getId(),
                                                            update.getMessage().getFrom().getUserName()));
        }
        message.setTextMessage(update.getMessage().getText());
        message.setEmployee(employee);

        return messageRepository.save(message);
    }
}
