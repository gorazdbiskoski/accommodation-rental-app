package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.events.HostCreatedEvent;
import mk.ukim.finki.accommodation_rental_backend.events.HostDeletedEvent;
import mk.ukim.finki.accommodation_rental_backend.events.HostUpdatedEvent;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.model.projections.HostProjection;
import mk.ukim.finki.accommodation_rental_backend.model.views.HostsPerCountryView;
import mk.ukim.finki.accommodation_rental_backend.repository.HostRepository;
import mk.ukim.finki.accommodation_rental_backend.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        Host saved = hostRepository.save(host);
        applicationEventPublisher.publishEvent(new HostCreatedEvent(saved));
        return Optional.of(saved);
    }

    @Override
    public Optional<Host> update(Host host, Long id) {
        return hostRepository.findById(id).map(oldHost -> {
            if(host.getName() != null)
            {
                oldHost.setName(host.getName());
            }
            if(host.getSurname() != null)
            {
                oldHost.setSurname(host.getSurname());
            }
            if(host.getCountry() != null)
            {
                oldHost.setCountry(host.getCountry());
            }
            applicationEventPublisher.publishEvent(new HostUpdatedEvent(oldHost));
            return hostRepository.save(oldHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        if(hostRepository.existsById(id))
        {
            applicationEventPublisher.publishEvent(new HostDeletedEvent(findById(id).get()));
        }
        hostRepository.deleteById(id);
    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountryView() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostProjection> findAllHostsNames() {
        return hostRepository.takeNameAndSurnameProjection();
    }
}
