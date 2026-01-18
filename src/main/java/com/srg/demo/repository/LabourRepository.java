package com.srg.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srg.demo.entity.Labour;

@Repository
public interface LabourRepository extends JpaRepository<Labour, Long> {

    Optional<Labour> findByEmail(String email);
    Optional<Labour> findByMobile(String mobile);

    @Query("""
        SELECT DISTINCT l FROM Labour l
        JOIN l.skills s
        WHERE l.status = 'ACTIVE'
        AND LOWER(TRIM(l.city)) = LOWER(TRIM(:city))
        AND LOWER(TRIM(l.area)) = LOWER(TRIM(:area))
        AND s.skillId IN :skillIds
    """)
    List<Labour> searchActiveLabours(
        @Param("city") String city,
        @Param("area") String area,
        @Param("skillIds") List<Long> skillIds
    );
}
