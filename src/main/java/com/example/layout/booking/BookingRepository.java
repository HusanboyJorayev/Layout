package com.example.layout.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByIdAndDeletedAtIsNull(Integer id);

    Page<Booking> findAllByDeletedAtIsNull(Pageable pageable);

    @Query("""
            select a from Booking as a
            """)
    List<Booking> getAll();
}
