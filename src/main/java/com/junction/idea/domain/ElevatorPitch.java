package com.junction.idea.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A ElevatorPitch.
 */
@Entity
@Table(name = "elevator_pitch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ElevatorPitch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "like_number")
    private Integer likeNumber;

    @Column(name = "liked")
    private Boolean liked;

    @ManyToOne
    private User inventor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ElevatorPitch id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public ElevatorPitch title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public ElevatorPitch description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public ElevatorPitch videoUrl(String videoUrl) {
        this.setVideoUrl(videoUrl);
        return this;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public ElevatorPitch thumbnailUrl(String thumbnailUrl) {
        this.setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getLikeNumber() {
        return this.likeNumber;
    }

    public ElevatorPitch likeNumber(Integer likeNumber) {
        this.setLikeNumber(likeNumber);
        return this;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Boolean getLiked() {
        return this.liked;
    }

    public ElevatorPitch liked(Boolean liked) {
        this.setLiked(liked);
        return this;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public User getInventor() {
        return this.inventor;
    }

    public void setInventor(User user) {
        this.inventor = user;
    }

    public ElevatorPitch inventor(User user) {
        this.setInventor(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElevatorPitch)) {
            return false;
        }
        return id != null && id.equals(((ElevatorPitch) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElevatorPitch{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", likeNumber=" + getLikeNumber() +
            ", liked='" + getLiked() + "'" +
            "}";
    }
}
