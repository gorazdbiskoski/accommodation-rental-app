package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;

import java.util.List;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        DisplayCountryDto country
) {
    public static DisplayHostDto from(Host host) {
        if(host != null)
        {
            return new DisplayHostDto(
                    host.getId(),
                    host.getName(),
                    host.getSurname(),
                    DisplayCountryDto.from(host.getCountry())
            );
        }
        return null;
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream()
                .map(DisplayHostDto::from)
                .toList();
    }
}
