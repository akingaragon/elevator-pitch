<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="elevatorPitchApp.elevatorPitch.home.createOrEditLabel" data-cy="ElevatorPitchCreateUpdateHeading">
          Submit Your Elevator Pitch
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
        </div>
        <div>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.elevatorPitch.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Submit Application</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./elevator-pitch-update.component.ts"></script>
