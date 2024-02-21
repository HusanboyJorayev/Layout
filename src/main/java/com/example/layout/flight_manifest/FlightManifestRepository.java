package com.example.layout.flight_manifest;

import com.example.layout.booking.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightManifestRepository extends JpaRepository<FlightManifest, Integer> {
    Optional<FlightManifest> findByIdAndDeletedAtIsNull(Integer id);

    Page<FlightManifest> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from FlightManifest as a
            """)
    List<FlightManifest> getAll();
}
