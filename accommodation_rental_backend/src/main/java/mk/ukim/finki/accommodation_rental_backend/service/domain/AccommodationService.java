package mk.ukim.finki.accommodation_rental_backend.service.domain;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Accommodation accommodation, Long id);
    void deleteById(Long id);

    boolean rentById(Long id);

    void addReservation(Long id);
    void removeReservation(Long id);
    void finalizeAllReservations();
    List<Accommodation> viewAllReservations();

    List<AccommodationsPerHostView> getAccommodationsPerHostView();

    void refreshAccommodationsPerHostView();
}
