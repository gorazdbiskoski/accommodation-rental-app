package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Category;

import java.util.List;

public record DisplayAccommodationDto(
        String name,
        Category category,
        DisplayHostDto host,
        Integer numRooms,
        Boolean isAvailable
) {

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        if(accommodation != null)
        {
            return new DisplayAccommodationDto(
                    accommodation.getName(),
                    accommodation.getCategory(),
                    DisplayHostDto.from(accommodation.getHost()),
                    accommodation.getNumRooms(),
                    accommodation.getIsAvailable()
            );
        }
        return null;
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(DisplayAccommodationDto::from)
                .toList();
    }
}
