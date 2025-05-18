package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Category;

public record CreateAccommodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        Boolean isAvailable
) {
    public Accommodation toEntity(Host host) {
        return new Accommodation(
                name,
                category,
                host,
                numRooms,
                isAvailable
        );
    }
}
