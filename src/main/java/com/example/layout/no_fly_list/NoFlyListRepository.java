package com.example.layout.no_fly_list;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoFlyListRepository extends JpaRepository<NoFlyList, Integer> {
    Optional<NoFlyList> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select a from NoFlyList as a
            """)
    List<NoFlyList> getAll();
}
