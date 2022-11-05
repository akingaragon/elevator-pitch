import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const ElevatorPitch = () => import('@/entities/elevator-pitch/elevator-pitch.vue');
// prettier-ignore
const ElevatorPitchUpdate = () => import('@/entities/elevator-pitch/elevator-pitch-update.vue');
// prettier-ignore
const ElevatorPitchDetails = () => import('@/entities/elevator-pitch/elevator-pitch-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'elevator-pitch',
      name: 'ElevatorPitch',
      component: ElevatorPitch,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'elevator-pitch/new',
      name: 'ElevatorPitchCreate',
      component: ElevatorPitchUpdate,
      meta: { authorities: [Authority.USER, Authority.INVENTOR] },
    },
    {
      path: 'elevator-pitch/:elevatorPitchId/edit',
      name: 'ElevatorPitchEdit',
      component: ElevatorPitchUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'elevator-pitch/:elevatorPitchId/view',
      name: 'ElevatorPitchView',
      component: ElevatorPitchDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
