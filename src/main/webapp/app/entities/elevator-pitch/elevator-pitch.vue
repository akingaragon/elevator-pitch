<template>
  <div>
    <h2 id="page-heading" data-cy="ElevatorPitchHeading">
      <span id="elevator-pitch-heading">Elevator Pitches</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ElevatorPitchCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-elevator-pitch"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Elevator Pitch </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && elevatorPitches && elevatorPitches.length === 0">
      <span>No elevatorPitches found</span>
    </div>
    <div class="table-responsive" v-if="elevatorPitches && elevatorPitches.length > 0">
      <table class="table table-striped" aria-describedby="elevatorPitches">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('title')">
              <span>Title</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span>Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('videoUrl')">
              <span>Video Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'videoUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('thumbnailUrl')">
              <span>Thumbnail Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'thumbnailUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('likeNumber')">
              <span>Like Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'likeNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="elevatorPitch in elevatorPitches" :key="elevatorPitch.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ElevatorPitchView', params: { elevatorPitchId: elevatorPitch.id } }">{{
                elevatorPitch.id
              }}</router-link>
            </td>
            <td>{{ elevatorPitch.title }}</td>
            <td>{{ elevatorPitch.description }}</td>
            <td>{{ elevatorPitch.videoUrl }}</td>
            <td>{{ elevatorPitch.thumbnailUrl }}</td>
            <td>{{ elevatorPitch.likeNumber }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ElevatorPitchView', params: { elevatorPitchId: elevatorPitch.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ElevatorPitchEdit', params: { elevatorPitchId: elevatorPitch.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(elevatorPitch)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
        <infinite-loading
          ref="infiniteLoading"
          v-if="totalItems > itemsPerPage"
          :identifier="infiniteId"
          slot="append"
          @infinite="loadMore"
          force-use-infinite-wrapper=".el-table__body-wrapper"
          :distance="20"
        >
        </infinite-loading>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="elevatorPitchApp.elevatorPitch.delete.question" data-cy="elevatorPitchDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-elevatorPitch-heading">Are you sure you want to delete this Elevator Pitch?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-elevatorPitch"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeElevatorPitch()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./elevator-pitch.component.ts"></script>
