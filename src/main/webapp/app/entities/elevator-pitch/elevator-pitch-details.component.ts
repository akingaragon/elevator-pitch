import { Component, Vue, Inject } from 'vue-property-decorator';

import { IElevatorPitch } from '@/shared/model/elevator-pitch.model';
import ElevatorPitchService from './elevator-pitch.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ElevatorPitchDetails extends Vue {
  @Inject('elevatorPitchService') private elevatorPitchService: () => ElevatorPitchService;
  @Inject('alertService') private alertService: () => AlertService;

  public elevatorPitch: IElevatorPitch = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.elevatorPitchId) {
        vm.retrieveElevatorPitch(to.params.elevatorPitchId);
      }
    });
  }

  public retrieveElevatorPitch(elevatorPitchId) {
    this.elevatorPitchService()
      .find(elevatorPitchId)
      .then(res => {
        this.elevatorPitch = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
