package com.example.layout.baggage;

import com.example.layout.airport.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    Optional<Baggage> findByIdAndDeletedAtIsNull(Integer id);

    Page<Baggage> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from Baggage as a
            """)
    List<Baggage> getAll();
}
