package com.junction.idea.web.rest;

import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.repository.ElevatorPitchRepository;
import com.junction.idea.service.ElevatorPitchService;
import com.junction.idea.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.junction.idea.domain.ElevatorPitch}.
 */
@RestController
@RequestMapping("/api")
public class ElevatorPitchResource {

    private final Logger log = LoggerFactory.getLogger(ElevatorPitchResource.class);

    private static final String ENTITY_NAME = "elevatorPitch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElevatorPitchService elevatorPitchService;

    private final ElevatorPitchRepository elevatorPitchRepository;

    public ElevatorPitchResource(ElevatorPitchService elevatorPitchService, ElevatorPitchRepository elevatorPitchRepository) {
        this.elevatorPitchService = elevatorPitchService;
        this.elevatorPitchRepository = elevatorPitchRepository;
    }

    /**
     * {@code POST  /elevator-pitches} : Create a new elevatorPitch.
     *
     * @param elevatorPitch the elevatorPitch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elevatorPitch, or with status {@code 400 (Bad Request)} if the elevatorPitch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elevator-pitches")
    public ResponseEntity<ElevatorPitch> createElevatorPitch(@RequestBody ElevatorPitch elevatorPitch) throws URISyntaxException {
        log.debug("REST request to save ElevatorPitch : {}", elevatorPitch);
        if (elevatorPitch.getId() != null) {
            throw new BadRequestAlertException("A new elevatorPitch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElevatorPitch result = elevatorPitchService.save(elevatorPitch);
        return ResponseEntity
            .created(new URI("/api/elevator-pitches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elevator-pitches/:id} : Updates an existing elevatorPitch.
     *
     * @param id the id of the elevatorPitch to save.
     * @param elevatorPitch the elevatorPitch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elevatorPitch,
     * or with status {@code 400 (Bad Request)} if the elevatorPitch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elevatorPitch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elevator-pitches/{id}")
    public ResponseEntity<ElevatorPitch> updateElevatorPitch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ElevatorPitch elevatorPitch
    ) throws URISyntaxException {
        log.debug("REST request to update ElevatorPitch : {}, {}", id, elevatorPitch);
        if (elevatorPitch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, elevatorPitch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!elevatorPitchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ElevatorPitch result = elevatorPitchService.update(elevatorPitch);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, elevatorPitch.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /elevator-pitches/:id} : Partial updates given fields of an existing elevatorPitch, field will ignore if it is null
     *
     * @param id the id of the elevatorPitch to save.
     * @param elevatorPitch the elevatorPitch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elevatorPitch,
     * or with status {@code 400 (Bad Request)} if the elevatorPitch is not valid,
     * or with status {@code 404 (Not Found)} if the elevatorPitch is not found,
     * or with status {@code 500 (Internal Server Error)} if the elevatorPitch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/elevator-pitches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ElevatorPitch> partialUpdateElevatorPitch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ElevatorPitch elevatorPitch
    ) throws URISyntaxException {
        log.debug("REST request to partial update ElevatorPitch partially : {}, {}", id, elevatorPitch);
        if (elevatorPitch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, elevatorPitch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!elevatorPitchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ElevatorPitch> result = elevatorPitchService.partialUpdate(elevatorPitch);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, elevatorPitch.getId().toString())
        );
    }

    /**
     * {@code GET  /elevator-pitches} : get all the elevatorPitches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elevatorPitches in body.
     */
    @GetMapping("/elevator-pitches")
    public ResponseEntity<List<ElevatorPitch>> getAllElevatorPitches(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ElevatorPitches");
        Page<ElevatorPitch> page = elevatorPitchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elevator-pitches/:id} : get the "id" elevatorPitch.
     *
     * @param id the id of the elevatorPitch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elevatorPitch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elevator-pitches/{id}")
    public ResponseEntity<ElevatorPitch> getElevatorPitch(@PathVariable Long id) {
        log.debug("REST request to get ElevatorPitch : {}", id);
        Optional<ElevatorPitch> elevatorPitch = elevatorPitchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elevatorPitch);
    }

    /**
     * {@code DELETE  /elevator-pitches/:id} : delete the "id" elevatorPitch.
     *
     * @param id the id of the elevatorPitch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elevator-pitches/{id}")
    public ResponseEntity<Void> deleteElevatorPitch(@PathVariable Long id) {
        log.debug("REST request to delete ElevatorPitch : {}", id);
        elevatorPitchService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
