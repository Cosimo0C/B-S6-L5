package cosimocrupi.L5.repositories;

import cosimocrupi.L5.entities.Employee;
import cosimocrupi.L5.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByDateRequest(LocalDate dateRequest);
}
