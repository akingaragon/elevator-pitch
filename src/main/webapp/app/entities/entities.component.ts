import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ElevatorPitchService from './elevator-pitch/elevator-pitch.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('elevatorPitchService') private elevatorPitchService = () => new ElevatorPitchService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
