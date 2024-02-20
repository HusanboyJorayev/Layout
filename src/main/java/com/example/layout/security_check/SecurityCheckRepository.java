package com.example.layout.security_check;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityCheckRepository extends JpaRepository<SecurityCheck, Integer> {
    Optional<SecurityCheck> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from SecurityCheck as a
            """)
    List<SecurityCheck> getAll();
}
