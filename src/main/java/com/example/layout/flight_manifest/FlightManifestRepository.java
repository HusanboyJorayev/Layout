package com.example.layout.flight_manifest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightManifestRepository extends JpaRepository<FlightManifest, Integer> {
    Optional<FlightManifest> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from FlightManifest as a
            """)
    List<FlightManifest> getAll();
}
