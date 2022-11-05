<template>
  <div class="home row">
    <div class="col-md-3">
      <span class="hipster img-fluid rounded"></span>
    </div>
    <div class="col-md-9">
      <h1 class="display-4">Welcome to Elevator Pitch!</h1>
      <p class="lead">Your idea turns into a real product</p>

      <div v-if="hasAnyAuthority('ROLE_INVENTOR') && authenticated">
        <div class="alert alert-success">
          <span v-if="username">You are logged in as user "{{ username }}"</span>
        </div>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-elevatorPitch"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeElevatorPitch()"
        >
          Create Pitch
        </button>
      </div>
      <div class="alert alert-warning" v-if="!authenticated">
        <span>You don't have an account yet?</span>&nbsp;
        <router-link class="alert-link" to="/register">Register a new account</router-link>
      </div>
      <p v-if="!authenticated">If you have any question on Elevator Pitch:</p>

      <ul v-if="!authenticated">
        <li><a href="#" target="_blank" rel="noopener noreferrer">Elevator Pitch homepage</a></li>
        <li>
          <a href="#" target="_blank" rel="noopener noreferrer">FAQ</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
