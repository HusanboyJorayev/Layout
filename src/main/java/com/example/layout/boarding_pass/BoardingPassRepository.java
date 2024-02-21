package com.example.layout.boarding_pass;

import com.example.layout.baggage_check.BaggageCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardingPassRepository  extends JpaRepository<BoardingPass,Integer> {
    Optional<BoardingPass> findByIdAndDeletedAtIsNull(Integer id);
    Page<BoardingPass> findAllByDeletedAtIsNull(Pageable pageable);


    @Query("""
            select a from BoardingPass as a
            """)
    List<BoardingPass> getAll();
}
