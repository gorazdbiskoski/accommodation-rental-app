package mk.ukim.finki.accommodation_rental_backend.service.application.impl;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.service.application.AccommodationApplicationService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.AccommodationService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        Host host = hostService.findById(accommodation.hostId()).orElse(null);

        return accommodationService.save(accommodation.toEntity(host))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(CreateAccommodationDto accommodation, Long id) {
        Host host = null;
        if(accommodation.hostId() != null && hostService.findById(accommodation.hostId()).isPresent())
        {
            host = hostService.findById(accommodation.hostId()).get();
        }

        return accommodationService.update(accommodation.toEntity(host), id)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public boolean rentById(Long id) {
        return accommodationService.rentById(id);
    }
}
