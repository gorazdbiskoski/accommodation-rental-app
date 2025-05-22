package mk.ukim.finki.accommodation_rental_backend.service.application.impl;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateHostDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayHostDto;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;
import mk.ukim.finki.accommodation_rental_backend.model.projections.HostProjection;
import mk.ukim.finki.accommodation_rental_backend.model.views.HostsPerCountryView;
import mk.ukim.finki.accommodation_rental_backend.service.application.HostApplicationService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.CountryService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Country country = countryService.findById(host.country()).orElse(null);

        return hostService.save(host.toEntity(country))
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(CreateHostDto host, Long id) {
        Country country = countryService.findById(host.country()).orElse(null);

        return hostService.update(host.toEntity(country), id)
                .map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountryView() {
        return hostService.getHostsPerCountryView();
    }

    @Override
    public List<HostProjection> findAllHostsNames() {
        return hostService.findAllHostsNames();
    }
}
