package kg.mega.remoteTgBot.models.entities;

import jakarta.persistence.*;
import kg.mega.remoteTgBot.models.entities.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String textMessage;
    @ManyToOne()
    Employee employee;

    Date date = new Date();
}
