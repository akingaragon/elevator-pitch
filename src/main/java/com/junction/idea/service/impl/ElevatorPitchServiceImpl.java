package com.junction.idea.service.impl;

import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.repository.ElevatorPitchRepository;
import com.junction.idea.service.ElevatorPitchService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ElevatorPitch}.
 */
@Service
@Transactional
public class ElevatorPitchServiceImpl implements ElevatorPitchService {

    private final Logger log = LoggerFactory.getLogger(ElevatorPitchServiceImpl.class);

    private final ElevatorPitchRepository elevatorPitchRepository;

    public ElevatorPitchServiceImpl(ElevatorPitchRepository elevatorPitchRepository) {
        this.elevatorPitchRepository = elevatorPitchRepository;
    }

    @Override
    public ElevatorPitch save(ElevatorPitch elevatorPitch) {
        log.debug("Request to save ElevatorPitch : {}", elevatorPitch);
        return elevatorPitchRepository.save(elevatorPitch);
    }

    @Override
    public ElevatorPitch update(ElevatorPitch elevatorPitch) {
        log.debug("Request to update ElevatorPitch : {}", elevatorPitch);
        return elevatorPitchRepository.save(elevatorPitch);
    }

    @Override
    public Optional<ElevatorPitch> partialUpdate(ElevatorPitch elevatorPitch) {
        log.debug("Request to partially update ElevatorPitch : {}", elevatorPitch);

        return elevatorPitchRepository
            .findById(elevatorPitch.getId())
            .map(existingElevatorPitch -> {
                if (elevatorPitch.getTitle() != null) {
                    existingElevatorPitch.setTitle(elevatorPitch.getTitle());
                }
                if (elevatorPitch.getDescription() != null) {
                    existingElevatorPitch.setDescription(elevatorPitch.getDescription());
                }
                if (elevatorPitch.getVideoUrl() != null) {
                    existingElevatorPitch.setVideoUrl(elevatorPitch.getVideoUrl());
                }
                if (elevatorPitch.getThumbnailUrl() != null) {
                    existingElevatorPitch.setThumbnailUrl(elevatorPitch.getThumbnailUrl());
                }
                if (elevatorPitch.getLikeNumber() != null) {
                    existingElevatorPitch.setLikeNumber(elevatorPitch.getLikeNumber());
                }
                if (elevatorPitch.getLiked() != null) {
                    existingElevatorPitch.setLiked(elevatorPitch.getLiked());
                }
                if (elevatorPitch.getInventor() != null) {
                    existingElevatorPitch.setInventor(elevatorPitch.getInventor());
                }

                return existingElevatorPitch;
            })
            .map(elevatorPitchRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ElevatorPitch> findAll(Pageable pageable) {
        log.debug("Request to get all ElevatorPitches");
        return elevatorPitchRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ElevatorPitch> findOne(Long id) {
        log.debug("Request to get ElevatorPitch : {}", id);
        return elevatorPitchRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElevatorPitch : {}", id);
        elevatorPitchRepository.deleteById(id);
    }
}
