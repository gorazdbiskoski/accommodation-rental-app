package mk.ukim.finki.accommodation_rental_backend.service.application;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateHostDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayHostDto;
import mk.ukim.finki.accommodation_rental_backend.model.projections.HostProjection;
import mk.ukim.finki.accommodation_rental_backend.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> save(CreateHostDto host);

    Optional<DisplayHostDto> update(CreateHostDto host, Long id);

    void deleteById(Long id);

    List<HostsPerCountryView> getHostsPerCountryView();

    List<HostProjection> findAllHostsNames();
}
