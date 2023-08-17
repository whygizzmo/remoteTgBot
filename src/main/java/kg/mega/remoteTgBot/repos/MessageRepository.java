package kg.mega.remoteTgBot.repos;

import kg.mega.remoteTgBot.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "SELECT * FROM message m WHERE m.employee_id = ?1 AND DATE_PART('day', NOW() - m.date) < 31", nativeQuery = true)
    List<Message> findAllByEmployeeId(long employeeId);
}
