package mk.ukim.finki.accommodation_rental_backend.service.domain;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.model.projections.HostProjection;
import mk.ukim.finki.accommodation_rental_backend.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);

    Optional<Host> update(Host host, Long id);

    void deleteById(Long id);

    List<HostsPerCountryView> getHostsPerCountryView();

    void refreshMaterializedView();

    List<HostProjection> findAllHostsNames();
}
