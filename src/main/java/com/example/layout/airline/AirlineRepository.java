package com.example.layout.airline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    Optional<Airline> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from Airline as a
            """)
    List<Airline> getAll();

    @Query("""
            select a.code from Airline as a order by a.id asc
            """)
    List<Integer> getAllAirlinesCode();
}
