package com.example.layout.baggage_check;

import com.example.layout.baggage.Baggage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaggageCheckRepository extends JpaRepository<BaggageCheck, Integer> {
    Optional<BaggageCheck> findByIdAndDeletedAtIsNull(Integer id);

    Page<BaggageCheck> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from BaggageCheck as a
            """)
    List<BaggageCheck> getAll();
}
