package com.junction.idea.service;

import com.junction.idea.service.dto.ElevatorPitchDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.junction.idea.domain.ElevatorPitch}.
 */
public interface ElevatorPitchService {
    /**
     * Save a elevatorPitch.
     *
     * @param elevatorPitchDTO the entity to save.
     * @return the persisted entity.
     */
    ElevatorPitchDTO save(ElevatorPitchDTO elevatorPitchDTO);

    /**
     * Updates a elevatorPitch.
     *
     * @param elevatorPitchDTO the entity to update.
     * @return the persisted entity.
     */
    ElevatorPitchDTO update(ElevatorPitchDTO elevatorPitchDTO);

    /**
     * Partially updates a elevatorPitch.
     *
     * @param elevatorPitchDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ElevatorPitchDTO> partialUpdate(ElevatorPitchDTO elevatorPitchDTO);

    /**
     * Get all the elevatorPitches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElevatorPitchDTO> findAll(Pageable pageable);

    /**
     * Get the "id" elevatorPitch.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElevatorPitchDTO> findOne(Long id);

    /**
     * Delete the "id" elevatorPitch.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
