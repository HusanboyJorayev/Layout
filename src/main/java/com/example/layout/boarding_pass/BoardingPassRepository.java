package com.example.layout.boarding_pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardingPassRepository  extends JpaRepository<BoardingPass,Integer> {
    Optional<BoardingPass> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from BoardingPass as a
            """)
    List<BoardingPass> getAll();
}
