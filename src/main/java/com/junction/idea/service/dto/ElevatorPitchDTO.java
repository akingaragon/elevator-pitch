package com.junction.idea.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.junction.idea.domain.ElevatorPitch} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ElevatorPitchDTO implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String videoUrl;

    private String thumbnailUrl;

    private Integer likeNumber;

    private Boolean liked;

    private Long inventor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Long getInventor() {
        return inventor;
    }

    public void setInventor(Long inventor) {
        this.inventor = inventor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElevatorPitchDTO)) {
            return false;
        }

        ElevatorPitchDTO elevatorPitchDTO = (ElevatorPitchDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, elevatorPitchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElevatorPitchDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", likeNumber=" + getLikeNumber() +
            ", liked='" + getLiked() + "'" +
            ", inventor=" + getInventor() +
            "}";
    }
}
