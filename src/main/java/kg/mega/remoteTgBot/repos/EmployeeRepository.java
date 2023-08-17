package kg.mega.remoteTgBot.repos;

import kg.mega.remoteTgBot.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByUserName(String username);
}
