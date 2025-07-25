package cosimocrupi.L5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate dateRequest;
    private String noteOrPreferences;
    private String employeeId;

    public Reservation(LocalDate dateRequest, String noteOrPreferences, String employeeId) {
        this.dateRequest = dateRequest;
        this.noteOrPreferences = noteOrPreferences;
        this.employeeId = employeeId;
    }

}
