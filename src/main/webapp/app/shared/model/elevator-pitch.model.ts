export interface IElevatorPitch {
  id?: number;
  title?: string | null;
  description?: string | null;
  videoUrl?: string | null;
  thumbnailUrl?: string | null;
  likeNumber?: number | null;
  liked?: boolean | null;
  inventor?: number | null;
}

export class ElevatorPitch implements IElevatorPitch {
  constructor(
    public id?: number,
    public title?: string | null,
    public description?: string | null,
    public videoUrl?: string | null,
    public thumbnailUrl?: string | null,
    public likeNumber?: number | null,
    public liked?: boolean | null,
    public inventor?: number | null
  ) {
    this.liked = this.liked ?? false;
  }
}
