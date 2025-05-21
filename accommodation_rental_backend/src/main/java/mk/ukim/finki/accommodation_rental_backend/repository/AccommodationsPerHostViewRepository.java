package mk.ukim.finki.accommodation_rental_backend.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.accommodation_rental_backend.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsPerHostView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.mv_accommodations_per_host", nativeQuery = true)
    void refreshMaterializedView();
}
