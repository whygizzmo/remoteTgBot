package kg.mega.remoteTgBot.repos;

import kg.mega.remoteTgBot.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByEmployeeId(long employeeId);
}
