package com.example.layout.flights;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Integer> {
    Optional<Flights> findByIdAndDeletedAtIsNull(Integer id);
    Page<Flights> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from Flights as a
            """)
    List<Flights> getAll();
}
