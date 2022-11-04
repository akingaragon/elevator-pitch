<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="elevatorPitch">
        <h2 class="jh-entity-heading" data-cy="elevatorPitchDetailsHeading"><span>ElevatorPitch</span> {{ elevatorPitch.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Title</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.title }}</span>
          </dd>
          <dt>
            <span>Description</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.description }}</span>
          </dd>
          <dt>
            <span>Video Url</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.videoUrl }}</span>
          </dd>
          <dt>
            <span>Thumbnail Url</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.thumbnailUrl }}</span>
          </dd>
          <dt>
            <span>Like Number</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.likeNumber }}</span>
          </dd>
          <dt>
            <span>Liked</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.liked }}</span>
          </dd>
          <dt>
            <span>Inventor</span>
          </dt>
          <dd>
            <span>{{ elevatorPitch.inventor }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="elevatorPitch.id"
          :to="{ name: 'ElevatorPitchEdit', params: { elevatorPitchId: elevatorPitch.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./elevator-pitch-details.component.ts"></script>
