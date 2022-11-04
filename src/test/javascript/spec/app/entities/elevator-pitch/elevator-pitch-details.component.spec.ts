/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ElevatorPitchDetailComponent from '@/entities/elevator-pitch/elevator-pitch-details.vue';
import ElevatorPitchClass from '@/entities/elevator-pitch/elevator-pitch-details.component';
import ElevatorPitchService from '@/entities/elevator-pitch/elevator-pitch.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ElevatorPitch Management Detail Component', () => {
    let wrapper: Wrapper<ElevatorPitchClass>;
    let comp: ElevatorPitchClass;
    let elevatorPitchServiceStub: SinonStubbedInstance<ElevatorPitchService>;

    beforeEach(() => {
      elevatorPitchServiceStub = sinon.createStubInstance<ElevatorPitchService>(ElevatorPitchService);

      wrapper = shallowMount<ElevatorPitchClass>(ElevatorPitchDetailComponent, {
        store,
        localVue,
        router,
        provide: { elevatorPitchService: () => elevatorPitchServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundElevatorPitch = { id: 123 };
        elevatorPitchServiceStub.find.resolves(foundElevatorPitch);

        // WHEN
        comp.retrieveElevatorPitch(123);
        await comp.$nextTick();

        // THEN
        expect(comp.elevatorPitch).toBe(foundElevatorPitch);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundElevatorPitch = { id: 123 };
        elevatorPitchServiceStub.find.resolves(foundElevatorPitch);

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
