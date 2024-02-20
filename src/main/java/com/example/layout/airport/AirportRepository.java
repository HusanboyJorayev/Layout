package com.example.layout.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from Airport as a
            """)
    List<Airport> getAll();
}
