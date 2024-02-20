package com.example.layout.baggage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    Optional<Baggage> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from Baggage as a
            """)
    List<Baggage> getAll();
}
