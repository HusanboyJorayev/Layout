package com.example.layout.airport;

import com.example.layout.airline.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByIdAndDeletedAtIsNull(Integer id);

    Page<Airport> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from Airport as a
            """)
    List<Airport> getAll();
}
