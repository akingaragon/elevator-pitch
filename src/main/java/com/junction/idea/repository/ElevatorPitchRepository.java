package com.junction.idea.repository;

import com.junction.idea.domain.ElevatorPitch;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ElevatorPitch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElevatorPitchRepository extends JpaRepository<ElevatorPitch, Long> {
    @Query("select elevatorPitch from ElevatorPitch elevatorPitch where elevatorPitch.inventor.login = ?#{principal.username}")
    List<ElevatorPitch> findByInventorIsCurrentUser();
}
