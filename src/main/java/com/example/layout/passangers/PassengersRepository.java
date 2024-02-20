package com.example.layout.passangers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengersRepository extends JpaRepository<Passengers, Integer> {
    Optional<Passengers> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from Passengers as a
            """)
    List<Passengers> getAll();
}
