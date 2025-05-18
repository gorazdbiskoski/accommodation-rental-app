package mk.ukim.finki.accommodation_rental_backend.service.domain;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Accommodation accommodation, Long id);
    void deleteById(Long id);

    boolean rentById(Long id);
}
