package com.junction.idea.service.impl;

import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.repository.ElevatorPitchRepository;
import com.junction.idea.repository.UserRepository;
import com.junction.idea.service.ElevatorPitchService;
import com.junction.idea.service.dto.ElevatorPitchDTO;
import com.junction.idea.service.mapper.ElevatorPitchMapper;
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

    private final ElevatorPitchMapper elevatorPitchMapper;

    public ElevatorPitchServiceImpl(ElevatorPitchRepository elevatorPitchRepository, ElevatorPitchMapper elevatorPitchMapper) {
        this.elevatorPitchRepository = elevatorPitchRepository;
        this.elevatorPitchMapper = elevatorPitchMapper;
    }

    @Override
    public ElevatorPitchDTO save(ElevatorPitchDTO elevatorPitchDTO) {
        log.debug("Request to save ElevatorPitch : {}", elevatorPitchDTO);
        ElevatorPitch elevatorPitch = elevatorPitchMapper.toEntity(elevatorPitchDTO);
        elevatorPitch = elevatorPitchRepository.save(elevatorPitch);
        return elevatorPitchMapper.toDto(elevatorPitch);
    }

    @Override
    public ElevatorPitchDTO update(ElevatorPitchDTO elevatorPitchDTO) {
        log.debug("Request to update ElevatorPitch : {}", elevatorPitchDTO);
        ElevatorPitch elevatorPitch = elevatorPitchMapper.toEntity(elevatorPitchDTO);
        elevatorPitch = elevatorPitchRepository.save(elevatorPitch);
        return elevatorPitchMapper.toDto(elevatorPitch);
    }

    @Override
    public Optional<ElevatorPitchDTO> partialUpdate(ElevatorPitchDTO elevatorPitchDTO) {
        log.debug("Request to partially update ElevatorPitch : {}", elevatorPitchDTO);

        return elevatorPitchRepository
            .findById(elevatorPitchDTO.getId())
            .map(existingElevatorPitch -> {
                elevatorPitchMapper.partialUpdate(existingElevatorPitch, elevatorPitchDTO);

                return existingElevatorPitch;
            })
            .map(elevatorPitchRepository::save)
            .map(elevatorPitchMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ElevatorPitchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElevatorPitches");
        return elevatorPitchRepository.findAll(pageable).map(elevatorPitchMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ElevatorPitchDTO> findOne(Long id) {
        log.debug("Request to get ElevatorPitch : {}", id);
        return elevatorPitchRepository.findById(id).map(elevatorPitchMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElevatorPitch : {}", id);
        elevatorPitchRepository.deleteById(id);
    }
}
