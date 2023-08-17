package kg.mega.remoteTgBot.services.impl;

import kg.mega.remoteTgBot.models.entities.Employee;
import kg.mega.remoteTgBot.models.entities.Message;
import kg.mega.remoteTgBot.repos.EmployeeRepository;
import kg.mega.remoteTgBot.repos.MessageRepository;
import kg.mega.remoteTgBot.services.MessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;
    final EmployeeRepository employeeRepository;

    public MessageServiceImpl(MessageRepository messageRepository, EmployeeRepository employeeRepository) {
        this.messageRepository = messageRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Message saveInDB(Update update) {
        Message message = new Message();
        //проверка на наличие сотрудника в базе
        Employee employee = employeeRepository.findByUserName(update.getMessage().getFrom().getUserName());
        if (employee == null) {
            employee = employeeRepository.save(new Employee(update.getMessage().getFrom().getId(),
                    update.getMessage().getFrom().getUserName()));
        }
        message.setTextMessage(update.getMessage().getText());
        message.setEmployee(employee);

        //сохранение в файл
        saveInFile(employee, message);
        return messageRepository.save(message);
    }

    @Override
    public void saveInFile(Employee employee, Message message) {


        String fileName = "C:\\Users\\дима\\Desktop\\mega\\remoteTgBot\\src\\main\\resources\\data.txt";
        String content = "Сообщение от польз-ля " + employee.getUserName() + ": " + message.getTextMessage() + "\nдата: " + message.getDate() + "\n\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
            System.out.println("Текст записан в файл успешно.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    @Override
    public String getAllMessagesForMonthFromEmployee(long employeeId) {
        String allMessageInString = "";

        List<Message> messageList = messageRepository.findAllByEmployeeId(employeeId);

        for (Message m : messageList) {
            allMessageInString += m.getDate()+"  ||  "+m.getTextMessage()+"\n";
        }

        return allMessageInString;
    }


}
