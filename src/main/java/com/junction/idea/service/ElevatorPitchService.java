package com.junction.idea.service;

import com.junction.idea.domain.ElevatorPitch;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link ElevatorPitch}.
 */
public interface ElevatorPitchService {
    /**
     * Save a elevatorPitch.
     *
     * @param elevatorPitch the entity to save.
     * @return the persisted entity.
     */
    ElevatorPitch save(ElevatorPitch elevatorPitch);

    /**
     * Updates a elevatorPitch.
     *
     * @param elevatorPitch the entity to update.
     * @return the persisted entity.
     */
    ElevatorPitch update(ElevatorPitch elevatorPitch);

    /**
     * Partially updates a elevatorPitch.
     *
     * @param elevatorPitch the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ElevatorPitch> partialUpdate(ElevatorPitch elevatorPitch);

    /**
     * Get all the elevatorPitches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElevatorPitch> findAll(Pageable pageable);

    /**
     * Get the "id" elevatorPitch.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElevatorPitch> findOne(Long id);

    /**
     * Delete the "id" elevatorPitch.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
