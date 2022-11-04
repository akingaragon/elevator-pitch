<template>
  <div class="home row">
    <div class="col-md-3">
      <span class="hipster img-fluid rounded"></span>
    </div>
    <div class="col-md-9">
      <h1 class="display-4">Welcome to Elevator Pitch!</h1>
      <p class="lead">Your idea turns into a real product</p>

      <div>
        <div class="alert alert-success" v-if="authenticated">
          <span v-if="username">You are logged in as user "{{ username }}"</span>
        </div>

        <div class="alert alert-warning" v-if="!authenticated">
          <span>You don't have an account yet?</span>&nbsp;
          <router-link class="alert-link" to="/register">Register a new account</router-link>
        </div>
      </div>

      <p>If you have any question on Elevator Pitch:</p>

      <ul>
        <li><a href="#" target="_blank" rel="noopener noreferrer">Elevator Pitch homepage</a></li>
        <li>
          <a href="#" target="_blank" rel="noopener noreferrer">FAQ</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
