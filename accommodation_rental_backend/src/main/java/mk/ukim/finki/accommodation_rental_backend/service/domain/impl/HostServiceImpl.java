package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.repository.HostRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
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
        return Optional.of(hostRepository.save(host));
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
            return hostRepository.save(oldHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
