/**
 * Интерфейс события
 */
export interface IEvent {
  id: number,
  name: string,
  description: string,
  link: string,
  dateStart: Date,
  dateEnd: Date
}
