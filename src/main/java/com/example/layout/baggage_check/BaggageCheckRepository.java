package com.example.layout.baggage_check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaggageCheckRepository extends JpaRepository<BaggageCheck, Integer> {
    Optional<BaggageCheck> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from BaggageCheck as a
            """)
    List<BaggageCheck> getAll();
}
