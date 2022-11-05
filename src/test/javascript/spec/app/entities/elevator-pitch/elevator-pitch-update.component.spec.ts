/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ElevatorPitchUpdateComponent from '@/entities/elevator-pitch/elevator-pitch-update.vue';
import ElevatorPitchClass from '@/entities/elevator-pitch/elevator-pitch-update.component';
import ElevatorPitchService from '@/entities/elevator-pitch/elevator-pitch.service';

import UserService from '@/entities/user/user.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('ElevatorPitch Management Update Component', () => {
    let wrapper: Wrapper<ElevatorPitchClass>;
    let comp: ElevatorPitchClass;
    let elevatorPitchServiceStub: SinonStubbedInstance<ElevatorPitchService>;

    beforeEach(() => {
      elevatorPitchServiceStub = sinon.createStubInstance<ElevatorPitchService>(ElevatorPitchService);

      wrapper = shallowMount<ElevatorPitchClass>(ElevatorPitchUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          elevatorPitchService: () => elevatorPitchServiceStub,
          alertService: () => new AlertService(),

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.elevatorPitch = entity;
        elevatorPitchServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(elevatorPitchServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.elevatorPitch = entity;
        elevatorPitchServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(elevatorPitchServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundElevatorPitch = { id: 123 };
        elevatorPitchServiceStub.find.resolves(foundElevatorPitch);
        elevatorPitchServiceStub.retrieve.resolves([foundElevatorPitch]);

        // WHEN
        comp.beforeRouteEnter({ params: { elevatorPitchId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.elevatorPitch).toBe(foundElevatorPitch);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
