package mk.ukim.finki.accommodation_rental_backend.service.application;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);
    Optional<DisplayAccommodationDto> update(CreateAccommodationDto accommodation, Long id);
    void deleteById(Long id);

    boolean rentById(Long id);

    void addReservation(Long id);
    void removeReservation(Long id);
    void finalizeAllReservations();
    List<DisplayAccommodationDto> viewAllReservations();

    List<AccommodationsPerHostView> getAccommodationsPerHostView();
}
