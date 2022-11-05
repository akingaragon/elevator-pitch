import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';

import { IElevatorPitch, ElevatorPitch } from '@/shared/model/elevator-pitch.model';
import ElevatorPitchService from './elevator-pitch.service';

const validations: any = {
  elevatorPitch: {
    title: {},
    description: {},
    videoUrl: {},
    thumbnailUrl: {},
    likeNumber: {},
    liked: {},
  },
};

@Component({
  validations,
})
export default class ElevatorPitchUpdate extends Vue {
  @Inject('elevatorPitchService') private elevatorPitchService: () => ElevatorPitchService;
  @Inject('alertService') private alertService: () => AlertService;

  public elevatorPitch: IElevatorPitch = new ElevatorPitch();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.elevatorPitchId) {
        vm.retrieveElevatorPitch(to.params.elevatorPitchId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.elevatorPitch.id) {
      this.elevatorPitchService()
        .update(this.elevatorPitch)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ElevatorPitch is updated with identifier ' + param.id;
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.elevatorPitchService()
        .create(this.elevatorPitch)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'Your ElevatorPitch is created successfully! Your application ID is ' + param.id;
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveElevatorPitch(elevatorPitchId): void {
    this.elevatorPitchService()
      .find(elevatorPitchId)
      .then(res => {
        this.elevatorPitch = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
