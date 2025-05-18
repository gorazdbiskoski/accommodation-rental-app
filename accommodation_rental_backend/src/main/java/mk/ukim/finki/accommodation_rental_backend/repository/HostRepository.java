package mk.ukim.finki.accommodation_rental_backend.repository;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
