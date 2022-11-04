package com.junction.idea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.junction.idea.IntegrationTest;
import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.repository.ElevatorPitchRepository;
import com.junction.idea.service.dto.ElevatorPitchDTO;
import com.junction.idea.service.mapper.ElevatorPitchMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ElevatorPitchResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ElevatorPitchResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_URL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_LIKE_NUMBER = 1;
    private static final Integer UPDATED_LIKE_NUMBER = 2;

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final Long DEFAULT_INVENTOR = 1L;
    private static final Long UPDATED_INVENTOR = 2L;

    private static final String ENTITY_API_URL = "/api/elevator-pitches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElevatorPitchRepository elevatorPitchRepository;

    @Autowired
    private ElevatorPitchMapper elevatorPitchMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElevatorPitchMockMvc;

    private ElevatorPitch elevatorPitch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElevatorPitch createEntity(EntityManager em) {
        ElevatorPitch elevatorPitch = new ElevatorPitch()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .videoUrl(DEFAULT_VIDEO_URL)
            .thumbnailUrl(DEFAULT_THUMBNAIL_URL)
            .likeNumber(DEFAULT_LIKE_NUMBER)
            .liked(DEFAULT_LIKED)
            .inventor(DEFAULT_INVENTOR);
        return elevatorPitch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElevatorPitch createUpdatedEntity(EntityManager em) {
        ElevatorPitch elevatorPitch = new ElevatorPitch()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .videoUrl(UPDATED_VIDEO_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .likeNumber(UPDATED_LIKE_NUMBER)
            .liked(UPDATED_LIKED)
            .inventor(UPDATED_INVENTOR);
        return elevatorPitch;
    }

    @BeforeEach
    public void initTest() {
        elevatorPitch = createEntity(em);
    }

    @Test
    @Transactional
    void createElevatorPitch() throws Exception {
        int databaseSizeBeforeCreate = elevatorPitchRepository.findAll().size();
        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);
        restElevatorPitchMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeCreate + 1);
        ElevatorPitch testElevatorPitch = elevatorPitchList.get(elevatorPitchList.size() - 1);
        assertThat(testElevatorPitch.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testElevatorPitch.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testElevatorPitch.getVideoUrl()).isEqualTo(DEFAULT_VIDEO_URL);
        assertThat(testElevatorPitch.getThumbnailUrl()).isEqualTo(DEFAULT_THUMBNAIL_URL);
        assertThat(testElevatorPitch.getLikeNumber()).isEqualTo(DEFAULT_LIKE_NUMBER);
        assertThat(testElevatorPitch.getLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testElevatorPitch.getInventor()).isEqualTo(DEFAULT_INVENTOR);
    }

    @Test
    @Transactional
    void createElevatorPitchWithExistingId() throws Exception {
        // Create the ElevatorPitch with an existing ID
        elevatorPitch.setId(1L);
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        int databaseSizeBeforeCreate = elevatorPitchRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restElevatorPitchMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllElevatorPitches() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        // Get all the elevatorPitchList
        restElevatorPitchMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elevatorPitch.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].videoUrl").value(hasItem(DEFAULT_VIDEO_URL)))
            .andExpect(jsonPath("$.[*].thumbnailUrl").value(hasItem(DEFAULT_THUMBNAIL_URL)))
            .andExpect(jsonPath("$.[*].likeNumber").value(hasItem(DEFAULT_LIKE_NUMBER)))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].inventor").value(hasItem(DEFAULT_INVENTOR.intValue())));
    }

    @Test
    @Transactional
    void getElevatorPitch() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        // Get the elevatorPitch
        restElevatorPitchMockMvc
            .perform(get(ENTITY_API_URL_ID, elevatorPitch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(elevatorPitch.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.videoUrl").value(DEFAULT_VIDEO_URL))
            .andExpect(jsonPath("$.thumbnailUrl").value(DEFAULT_THUMBNAIL_URL))
            .andExpect(jsonPath("$.likeNumber").value(DEFAULT_LIKE_NUMBER))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.inventor").value(DEFAULT_INVENTOR.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingElevatorPitch() throws Exception {
        // Get the elevatorPitch
        restElevatorPitchMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingElevatorPitch() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();

        // Update the elevatorPitch
        ElevatorPitch updatedElevatorPitch = elevatorPitchRepository.findById(elevatorPitch.getId()).get();
        // Disconnect from session so that the updates on updatedElevatorPitch are not directly saved in db
        em.detach(updatedElevatorPitch);
        updatedElevatorPitch
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .videoUrl(UPDATED_VIDEO_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .likeNumber(UPDATED_LIKE_NUMBER)
            .liked(UPDATED_LIKED)
            .inventor(UPDATED_INVENTOR);
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(updatedElevatorPitch);

        restElevatorPitchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, elevatorPitchDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isOk());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
        ElevatorPitch testElevatorPitch = elevatorPitchList.get(elevatorPitchList.size() - 1);
        assertThat(testElevatorPitch.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testElevatorPitch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testElevatorPitch.getVideoUrl()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testElevatorPitch.getThumbnailUrl()).isEqualTo(UPDATED_THUMBNAIL_URL);
        assertThat(testElevatorPitch.getLikeNumber()).isEqualTo(UPDATED_LIKE_NUMBER);
        assertThat(testElevatorPitch.getLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testElevatorPitch.getInventor()).isEqualTo(UPDATED_INVENTOR);
    }

    @Test
    @Transactional
    void putNonExistingElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, elevatorPitchDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateElevatorPitchWithPatch() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();

        // Update the elevatorPitch using partial update
        ElevatorPitch partialUpdatedElevatorPitch = new ElevatorPitch();
        partialUpdatedElevatorPitch.setId(elevatorPitch.getId());

        partialUpdatedElevatorPitch
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .videoUrl(UPDATED_VIDEO_URL)
            .inventor(UPDATED_INVENTOR);

        restElevatorPitchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElevatorPitch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElevatorPitch))
            )
            .andExpect(status().isOk());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
        ElevatorPitch testElevatorPitch = elevatorPitchList.get(elevatorPitchList.size() - 1);
        assertThat(testElevatorPitch.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testElevatorPitch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testElevatorPitch.getVideoUrl()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testElevatorPitch.getThumbnailUrl()).isEqualTo(DEFAULT_THUMBNAIL_URL);
        assertThat(testElevatorPitch.getLikeNumber()).isEqualTo(DEFAULT_LIKE_NUMBER);
        assertThat(testElevatorPitch.getLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testElevatorPitch.getInventor()).isEqualTo(UPDATED_INVENTOR);
    }

    @Test
    @Transactional
    void fullUpdateElevatorPitchWithPatch() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();

        // Update the elevatorPitch using partial update
        ElevatorPitch partialUpdatedElevatorPitch = new ElevatorPitch();
        partialUpdatedElevatorPitch.setId(elevatorPitch.getId());

        partialUpdatedElevatorPitch
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .videoUrl(UPDATED_VIDEO_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .likeNumber(UPDATED_LIKE_NUMBER)
            .liked(UPDATED_LIKED)
            .inventor(UPDATED_INVENTOR);

        restElevatorPitchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElevatorPitch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElevatorPitch))
            )
            .andExpect(status().isOk());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
        ElevatorPitch testElevatorPitch = elevatorPitchList.get(elevatorPitchList.size() - 1);
        assertThat(testElevatorPitch.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testElevatorPitch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testElevatorPitch.getVideoUrl()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testElevatorPitch.getThumbnailUrl()).isEqualTo(UPDATED_THUMBNAIL_URL);
        assertThat(testElevatorPitch.getLikeNumber()).isEqualTo(UPDATED_LIKE_NUMBER);
        assertThat(testElevatorPitch.getLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testElevatorPitch.getInventor()).isEqualTo(UPDATED_INVENTOR);
    }

    @Test
    @Transactional
    void patchNonExistingElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, elevatorPitchDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamElevatorPitch() throws Exception {
        int databaseSizeBeforeUpdate = elevatorPitchRepository.findAll().size();
        elevatorPitch.setId(count.incrementAndGet());

        // Create the ElevatorPitch
        ElevatorPitchDTO elevatorPitchDTO = elevatorPitchMapper.toDto(elevatorPitch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElevatorPitchMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(elevatorPitchDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ElevatorPitch in the database
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteElevatorPitch() throws Exception {
        // Initialize the database
        elevatorPitchRepository.saveAndFlush(elevatorPitch);

        int databaseSizeBeforeDelete = elevatorPitchRepository.findAll().size();

        // Delete the elevatorPitch
        restElevatorPitchMockMvc
            .perform(delete(ENTITY_API_URL_ID, elevatorPitch.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElevatorPitch> elevatorPitchList = elevatorPitchRepository.findAll();
        assertThat(elevatorPitchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
