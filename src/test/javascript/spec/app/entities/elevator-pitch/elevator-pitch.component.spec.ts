/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ElevatorPitchComponent from '@/entities/elevator-pitch/elevator-pitch.vue';
import ElevatorPitchClass from '@/entities/elevator-pitch/elevator-pitch.component';
import ElevatorPitchService from '@/entities/elevator-pitch/elevator-pitch.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('ElevatorPitch Management Component', () => {
    let wrapper: Wrapper<ElevatorPitchClass>;
    let comp: ElevatorPitchClass;
    let elevatorPitchServiceStub: SinonStubbedInstance<ElevatorPitchService>;

    beforeEach(() => {
      elevatorPitchServiceStub = sinon.createStubInstance<ElevatorPitchService>(ElevatorPitchService);
      elevatorPitchServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ElevatorPitchClass>(ElevatorPitchComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          elevatorPitchService: () => elevatorPitchServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      elevatorPitchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllElevatorPitchs();
      await comp.$nextTick();

      // THEN
      expect(elevatorPitchServiceStub.retrieve.called).toBeTruthy();
      expect(comp.elevatorPitches[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      elevatorPitchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(elevatorPitchServiceStub.retrieve.called).toBeTruthy();
      expect(comp.elevatorPitches[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      elevatorPitchServiceStub.retrieve.reset();
      elevatorPitchServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(elevatorPitchServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.elevatorPitches[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      elevatorPitchServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(elevatorPitchServiceStub.retrieve.callCount).toEqual(1);

      comp.removeElevatorPitch();
      await comp.$nextTick();

      // THEN
      expect(elevatorPitchServiceStub.delete.called).toBeTruthy();
      expect(elevatorPitchServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
