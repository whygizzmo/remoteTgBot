package kg.mega.remoteTgBot.services.impl;

import kg.mega.remoteTgBot.models.entities.Employee;
import kg.mega.remoteTgBot.repos.EmployeeRepository;
import kg.mega.remoteTgBot.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getListEmployee() {
        String allEmployeeInString = "";

        List<Employee> employeeList = employeeRepository.findAll();
        for ( Employee e:employeeList ) {
            allEmployeeInString+= e.getId()+". "+e.getUserName()+"\n";
        }
        allEmployeeInString+= "\n Введите айди сотрудника данные о котором хотите получить: ";


        return allEmployeeInString;
    }
}
