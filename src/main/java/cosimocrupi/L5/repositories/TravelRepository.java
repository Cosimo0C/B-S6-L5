package cosimocrupi.L5.repositories;

import cosimocrupi.L5.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TravelRepository extends JpaRepository<Travel, UUID> {
    Optional<Travel> findByReservationId(String reservationId);

}
