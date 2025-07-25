package cosimocrupi.L5.repositories;

import cosimocrupi.L5.entities.Reservation;
import cosimocrupi.L5.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TravelRepository extends JpaRepository<Travel, UUID> {
    List<Travel> findByDestination(String destination);

}
