<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="elevatorPitchApp.elevatorPitch.home.createOrEditLabel" data-cy="ElevatorPitchCreateUpdateHeading">
          Create or edit a ElevatorPitch
        </h2>
        <div>
          <div class="form-group" v-if="elevatorPitch.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="elevatorPitch.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="elevator-pitch-title"
              data-cy="title"
              :class="{ valid: !$v.elevatorPitch.title.$invalid, invalid: $v.elevatorPitch.title.$invalid }"
              v-model="$v.elevatorPitch.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="elevator-pitch-description"
              data-cy="description"
              :class="{ valid: !$v.elevatorPitch.description.$invalid, invalid: $v.elevatorPitch.description.$invalid }"
              v-model="$v.elevatorPitch.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-videoUrl">Video Url</label>
            <input
              type="text"
              class="form-control"
              name="videoUrl"
              id="elevator-pitch-videoUrl"
              data-cy="videoUrl"
              :class="{ valid: !$v.elevatorPitch.videoUrl.$invalid, invalid: $v.elevatorPitch.videoUrl.$invalid }"
              v-model="$v.elevatorPitch.videoUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-thumbnailUrl">Thumbnail Url</label>
            <input
              type="text"
              class="form-control"
              name="thumbnailUrl"
              id="elevator-pitch-thumbnailUrl"
              data-cy="thumbnailUrl"
              :class="{ valid: !$v.elevatorPitch.thumbnailUrl.$invalid, invalid: $v.elevatorPitch.thumbnailUrl.$invalid }"
              v-model="$v.elevatorPitch.thumbnailUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-likeNumber">Like Number</label>
            <input
              type="number"
              class="form-control"
              name="likeNumber"
              id="elevator-pitch-likeNumber"
              data-cy="likeNumber"
              :class="{ valid: !$v.elevatorPitch.likeNumber.$invalid, invalid: $v.elevatorPitch.likeNumber.$invalid }"
              v-model.number="$v.elevatorPitch.likeNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-liked">Liked</label>
            <input
              type="checkbox"
              class="form-check"
              name="liked"
              id="elevator-pitch-liked"
              data-cy="liked"
              :class="{ valid: !$v.elevatorPitch.liked.$invalid, invalid: $v.elevatorPitch.liked.$invalid }"
              v-model="$v.elevatorPitch.liked.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="elevator-pitch-inventor">Inventor</label>
            <input
              type="number"
              class="form-control"
              name="inventor"
              id="elevator-pitch-inventor"
              data-cy="inventor"
              :class="{ valid: !$v.elevatorPitch.inventor.$invalid, invalid: $v.elevatorPitch.inventor.$invalid }"
              v-model.number="$v.elevatorPitch.inventor.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.elevatorPitch.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./elevator-pitch-update.component.ts"></script>
