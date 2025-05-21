package mk.ukim.finki.accommodation_rental_backend.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("select * from public.mv_hosts_per_country")
@Immutable
public class HostsPerCountryView {
    @Id
    @Column(name = "country_id")
    private Long country_id;

    @Column(name = "num_hosts")
    private Integer numHosts;
}
